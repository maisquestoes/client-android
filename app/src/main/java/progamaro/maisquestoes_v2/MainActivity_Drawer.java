package progamaro.maisquestoes_v2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by helio on 22/07/15.
 */
public class MainActivity_Drawer extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private NavigationView _navigation_view;
    private DrawerLayout _drawer_layout;
    private Toolbar _toolbar;

    private LoginButton btn_login_fb;

    private GoogleApiClient _googleApiClient;
    private Location _lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        buildGoogleApiClient();

        FacebookSdk.sdkInitialize(getApplicationContext());

        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);

        _drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar _actionbar = getSupportActionBar();
        _actionbar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        _actionbar.setDisplayHomeAsUpEnabled(true);

        _navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        _navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                _drawer_layout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.mnu_first_fragment:
                        Toast.makeText(getApplicationContext(), "fragment User Indo", Toast.LENGTH_SHORT).show();
                        Fragment_user_info fragment_user_info = new Fragment_user_info();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, fragment_user_info);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.mnu_logout:
                        Logout();

                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "something", Toast.LENGTH_SHORT).show();
                        return true;
                }

            }
        });
    }

    private void Logout() {
        // get provider shared preferences
        SharedPreferences _preferences = getSharedPreferences("LoginPreferences", 0);
        String provider = _preferences.getString("prefUserProvider", "");

        VolleyApplication.clearSharedPreferences(getApplicationContext(), "LoginPreferences", "prefUserName");
        VolleyApplication.clearSharedPreferences(getApplicationContext(), "LoginPreferences", "prefName");
        VolleyApplication.clearSharedPreferences(getApplicationContext(), "LoginPreferences", "prefUserEmail");
        VolleyApplication.clearSharedPreferences(getApplicationContext(), "LoginPreferences", "prefUserProvider");

        if (provider.equals(VolleyApplication.provider.facebook.toString())) {
            // logout facebook TODO
        }

        startActivity(new Intent(MainActivity_Drawer.this, Login.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                _drawer_layout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private synchronized void buildGoogleApiClient() {
        _googleApiClient = new GoogleApiClient.Builder(MainActivity_Drawer.this)
                .addConnectionCallbacks(MainActivity_Drawer.this)
                .addOnConnectionFailedListener(MainActivity_Drawer.this)
                .addApi(LocationServices.API)
                .build();
    }

    // Methods implements by GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
    @Override
    public void onConnected(Bundle connectionHint) {
        _lastLocation = LocationServices.FusedLocationApi.getLastLocation(_googleApiClient);
        if (_lastLocation != null) {
            Toast.makeText(MainActivity_Drawer.this,
                    "Latitude: " + String.valueOf(_lastLocation.getLatitude()) +
                            "Longitude: " + String.valueOf(_lastLocation.getLongitude()),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
