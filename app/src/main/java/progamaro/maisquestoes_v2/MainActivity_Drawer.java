package progamaro.maisquestoes_v2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.mikepenz.materialdrawer.Drawer   ;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import progamaro.maisquestoes_v2.adapters.TabsAdapter;
import progamaro.maisquestoes_v2.dto.SigninDTO;
import progamaro.maisquestoes_v2.helpers.Preferences;

/**
 * Created by helio on 22/07/15.
 */
public class MainActivity_Drawer extends AppCompatActivity {

    //private NavigationView _navigation_view;
    private DrawerLayout _drawer_layout;
    private Drawer _navigationDrawerLeft;
    private Drawer _navigationDrawerRight;
    private AccountHeader _headerNavigationLeft;
    private Toolbar _toolbar;

    private SlidingTabLayout _SlidingTabLayout;
    private ViewPager _ViewPager;
    private TabsAdapter _TabsAdapter;

    private int _postionOldClicked = 0;

    /*private OnCheckedChangeListener _OnCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(MainActivity_Drawer.this, "onCheckedChange", Toast.LENGTH_LONG).show();
        }
    };*/

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
        _toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_search));

        _drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        /*ActionBar _actionbar = getSupportActionBar();
        _actionbar.setDisplayHomeAsUpEnabled(true);
        _actionbar.setHomeAsUpIndicator(getResources().getDrawable(R.mipmap.ic_search));*/

        // TABS
        _TabsAdapter = new TabsAdapter(getSupportFragmentManager(), MainActivity_Drawer.this);
        _ViewPager = (ViewPager)findViewById(R.id.vp_tabs);
        _ViewPager.setAdapter(_TabsAdapter);

        _SlidingTabLayout = (SlidingTabLayout)findViewById(R.id.stl_tabs);
        _SlidingTabLayout.setDistributeEvenly(true);
        _SlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        _SlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        _SlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //_navigationDrawerLeft.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        _SlidingTabLayout.setViewPager(_ViewPager);

        // NAVIGATION VIEW
        /*_navigation_view = (NavigationView) findViewById(R.id.navigation_view);
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
                        //fragmentTransaction.replace(R.id.content_frame, fragmentGps);
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
        });*/

        //NAVIGATION HEADER
        /*_headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                //.withHeaderBackground(R.mipmap.feijoada)
                .addProfiles(
                        new ProfileDrawerItem().withName("Hélio Feliciano").withEmail("heliofeliciano@gmail.com").withIcon(getResources().getDrawable(R.mipmap.newlogo2))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
                        Toast.makeText(MainActivity_Drawer.this, "onProfileChanged: "+iProfile.getName(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();*/

        // NAVIGATION DRAWER
        /*_navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(_toolbar)
                .withDisplayBelowToolbar(true)
                .withActionBarDrawerToggleAnimated(false)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withAccountHeader(_headerNavigationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                        _ViewPager.setCurrentItem(i);
                        *//*for (int count = 0, tam = _navigationDrawerLeft.getDrawerItems().size(); count < tam; count++) {
                            if (count == _postionOldClicked && _postionOldClicked <= 3) {
                                PrimaryDrawerItem aux = (PrimaryDrawerItem) _navigationDrawerLeft.getDrawerItems().get(count);
                                aux.setIcon(getResources().getDrawable(R.mipmap.logo_mq));
                                break;
                            }
                        }
                        return false;*//*
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity_Drawer.this, "onItemLongClick", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();
        _navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("TESTE 01").withIcon(getResources().getDrawable(R.mipmap.newlogo2)));
        _navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("TESTE 01").withIcon(getResources().getDrawable(R.mipmap.newlogo2)));
        _navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("TESTE 01").withIcon( getResources().getDrawable(R.mipmap.newlogo2) ));
        _navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("TESTE 01").withIcon( getResources().getDrawable(R.mipmap.newlogo2) ));
        _navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Configurações"));
        _navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notificação").withChecked(true).withOnCheckedChangeListener(_OnCheckedChangeListener));
        _navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("News").withChecked(true).withOnCheckedChangeListener(_OnCheckedChangeListener));*/



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
                //_drawer_layout.openDrawer(GravityCompat.START);
                Toast.makeText(MainActivity_Drawer.this, "Faça o que quiser aqui", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_logout:
                Toast.makeText(MainActivity_Drawer.this, "Saindo", Toast.LENGTH_LONG).show();
                Logout();
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        /*SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        */

        return true;
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
