package progamaro.maisquestoes_v2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by helio on 22/07/15.
 */
public class MainActivity_Drawer extends AppCompatActivity {

    private NavigationView _navigation_view;
    private DrawerLayout _drawer_layout;
    private Toolbar _toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        _toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);

        _drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ActionBar _actionbar = getSupportActionBar();
        _actionbar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        _actionbar.setDisplayHomeAsUpEnabled(true);

        _navigation_view = (NavigationView)findViewById(R.id.navigation_view);
        _navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()){
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                _drawer_layout.closeDrawers();

                switch (menuItem.getItemId()){

                }

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
}
