package progamaro.maisquestoes_v2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.sqlite.DbOpenHelper;
import progamaro.maisquestoes_v2.sqlite.DbQuestionHelper;

/**
 * Created by helio on 17/07/15.
 */
public class Fragment_user_social extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_info_social, null);
        final FragmentManager fm = getActivity().getSupportFragmentManager();

        final String[] lista = new String[]{"Hélio Feliciano", "André Miranda", "Grécio Beline", "Xico"};

        DbQuestionHelper qHelper = new DbQuestionHelper(new DbOpenHelper(getActivity()));

        ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, qHelper.GetQuestions());

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lista);
        ListView lv_main_social = (ListView)view.findViewById(R.id.lv_main_social);
        lv_main_social.setAdapter(adapter);

        lv_main_social.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String _name = lista[position];

                FragmentGps fragment_gps = (FragmentGps) fm.findFragmentById(R.id.frag_info_user);
                fragment_gps.setName(_name);

                //Toast.makeText(getActivity().getApplicationContext(), ""+position, Toast.LENGTH_LONG).show();

            }
        });


        return view;
    }
}
