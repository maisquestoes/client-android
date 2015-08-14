package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import progamaro.maisquestoes_v2.FragFavoritos;
import progamaro.maisquestoes_v2.FragSubjects;
import progamaro.maisquestoes_v2.Fragment2;
import progamaro.maisquestoes_v2.Fragment3;

/**
 * Created by helio on 05/08/15.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] titles = {"FAVORITOS", "TODOS", "PERFIL"};

    public TabsAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        mContext = ctx;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        switch (position){
            case 0:
                frag = new FragFavoritos();
                break;
            case 1:
                frag = new FragSubjects();
                break;
            case 2:
                frag = new FragFavoritos();
                break;
        }

        return frag;

    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
