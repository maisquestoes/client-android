package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import progamaro.maisquestoes_v2.Fragment1;
import progamaro.maisquestoes_v2.Fragment2;
import progamaro.maisquestoes_v2.Fragment3;
import progamaro.maisquestoes_v2.SubjectsFrag;

/**
 * Created by helio on 05/08/15.
 */
public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private String[] titles = {"HISTÓRICO", "ASSUNTOS", "ESTUDO"};

    public TabsAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        mContext = ctx;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Fragment1 frag = new Fragment1();
                return frag;
            case 1:
                Fragment2 frag2 = new Fragment2();
                return frag2;
            case 2:
                SubjectsFrag frag3 = new SubjectsFrag();
                return frag3;
            default:
                return null;
        }

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
