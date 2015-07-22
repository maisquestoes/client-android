package progamaro.maisquestoes_v2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by helio on 17/07/15.
 */
public class Fragment_user_info extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_info_user, null);
        return view;
    }

    public void setName(String name){
        TextView tv = (TextView)getView().findViewById(R.id.tv_frag_user_name);
        tv.setText(name);
    }
}
