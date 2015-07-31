package progamaro.maisquestoes_v2;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by helio on 30/07/15.
 */
public class FragmentGps extends Fragment
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient _googleApiClient;
    private Location _lastLocation;
    private Location _currentLocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private LocationRequest _locationRequest;
    private boolean _requestLocationUpdates = false;

    @Override
    public void onStart() {
        super.onStart();
        if (_googleApiClient != null){
            _googleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        stopLocationUpdates();
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(_googleApiClient, FragmentGps.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkPlayServices();
        if (_googleApiClient.isConnected() && !_requestLocationUpdates){
            startLocationUpdates();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_gps, null);

        if (checkPlayServices()) {
            buildGoogleApiClient();
            createLocationRequest();
        }

        return view;
    }

    public void setName(String name){
        TextView tv = (TextView)getView().findViewById(R.id.tv_frag_user_name);
        tv.setText(name);
    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        _googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        _currentLocation = location;
        updateUI();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("TESTE GPS", "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    private synchronized void buildGoogleApiClient() {
        _googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(FragmentGps.this)
                .addOnConnectionFailedListener(FragmentGps.this)
                .addApi(LocationServices.API)
                .build();
    }

    private void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                _googleApiClient,
                _locationRequest,
                FragmentGps.this);
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS){
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getActivity(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                //finish();
            }
            return false;
        }
        return true;
    }

    protected void createLocationRequest() {
        _locationRequest = new LocationRequest();
        _locationRequest.setInterval(3000);
        _locationRequest.setFastestInterval(5000);
        _locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void updateUI() {
        Toast.makeText(getActivity(),
                        "Latitude: " + String.valueOf(_currentLocation.getLatitude()) +
                        "Longitude: " + String.valueOf(_currentLocation.getLongitude()),
                Toast.LENGTH_LONG)
                .show();
    }
}
