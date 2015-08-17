package progamaro.maisquestoes_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import progamaro.maisquestoes_v2.adapters.ListSubjectAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;
import progamaro.maisquestoes_v2.helpers.GsonHelper;
import progamaro.maisquestoes_v2.helpers.Preferences;
import progamaro.maisquestoes_v2.helpers.Routes;
import progamaro.maisquestoes_v2.sqlite.DbOpenHelper;
import progamaro.maisquestoes_v2.sqlite.DbQuestionHelper;
import progamaro.maisquestoes_v2.sqlite.DbSubjectsFavoritesHelper;

/**
 * Created by andremiranda on 14/08/15.
 */
public class FragSubjects extends android.support.v4.app.Fragment {

    private ListView _lv_tab_subjects;
    private Button btn_tab_subjects;
    private ListSubjectAdapter _subjectAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_subjects,null);

        init(view);

//        GetSubjects();
        GetSubjectsSqLite();

        return view;
    }

    private void GetSubjectsSqLite(){
        DbSubjectsFavoritesHelper subjectsHelper = new DbSubjectsFavoritesHelper(new DbOpenHelper(getActivity()));
        List<SubjectsDTO> subjects = subjectsHelper.GetSubjects();

        _subjectAdapter = new ListSubjectAdapter(getActivity(),R.layout.tab_subjects_subject, subjects);
        _lv_tab_subjects.setAdapter(_subjectAdapter);
    }

    private void GetSubjects() {

//        startProgressBar();

        final String apikey = Preferences.getApiKey(getActivity());

        StringRequest request = new StringRequest(Request.Method.GET, Routes.ALL_SUBJECTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                List<SubjectsDTO> _listObjects = GsonHelper.fromJsonList(response, new SubjectsDTO());

                _subjectAdapter = new ListSubjectAdapter(getActivity(),R.layout.tab_subjects_subject, _listObjects);
                _lv_tab_subjects.setAdapter(_subjectAdapter);

//                _progressDialog.dismiss();

//                Preferences.setObjectPreference(getApplicationContext(), Preferences.LOGIN_PREFERENCES, _signinDTO);

                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                params.put("apikey", apikey);

                return params;
            }
        };
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    private void init(View view) {

        btn_tab_subjects = (Button) view.findViewById(R.id.btn_tab_subjects);
        _lv_tab_subjects = (ListView) view.findViewById(R.id.lv_tab_subjects);

    }
}
