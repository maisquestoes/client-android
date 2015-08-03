package progamaro.maisquestoes_v2;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import progamaro.maisquestoes_v2.dto.SigninDTO;
import progamaro.maisquestoes_v2.helpers.Preferences;

/**
 * Created by helio on 22/07/15.
 */
public class MainActivity_Drawer extends AppCompatActivity {

    private NavigationView _navigation_view;
    private DrawerLayout _drawer_layout;
    private Toolbar _toolbar;

    private LoginButton btn_login_fb;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // analytics facebook
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // analytics facebook
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

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
                        FragmentGps fragmentGps = new FragmentGps();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, fragmentGps);
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
        Preferences.clearObjectPreference(getApplicationContext(),Preferences.LOGIN_PREFERENCES,new SigninDTO());

        LoginManager.getInstance().logOut();

        startActivity(new Intent(MainActivity_Drawer.this, SigninPreview.class));
        finish();
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

}
