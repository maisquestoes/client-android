package progamaro.maisquestoes_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by andremiranda on 14/08/15.
 */
public class FragSubjects extends Fragment {

    private ListView lv_tab_subjects;
    private Button btn_tab_subjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_subjects,null);

        init(container);
        return view;
    }

    private void init(ViewGroup container) {

        btn_tab_subjects = (Button) container.findViewById(R.id.btn_tab_subjects);
        lv_tab_subjects = (ListView) container.findViewById(R.id.lv_tab_subjects);

    }
}
