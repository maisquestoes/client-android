package progamaro.maisquestoes_v2;

import android.app.SearchManager;
import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.adapters.SubjectsFavoriteViewAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;
import progamaro.maisquestoes_v2.sqlite.DbOpenHelper;
import progamaro.maisquestoes_v2.sqlite.DbSubjectsFavoritesHelper;

/**
 * Created by helio on 13/07/15.
 */
public class FragFavoritos extends Fragment {

    private GridView _gridview;
    private DbOpenHelper _db;
    private SubjectsFavoriteViewAdapter _subjectsViewAdapter;
    private List<SubjectsDTO> _listSubjects;
    private DbSubjectsFavoritesHelper _dbFavorites;
    private SubjectsDTO _subject;
    private LinearLayout _ll_favorites_last_studied;
    private ImageView _iv_favorites_material;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_favorities, container, false);

        InitSearchView();
        init(view);

        _ll_favorites_last_studied = (LinearLayout) view.findViewById(R.id.ll_favorites_last_studied);

        _ll_favorites_last_studied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "teste", Toast.LENGTH_SHORT).show();
            }
        });

        new Favorites().execute("list");

        _iv_favorites_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "aqui clica", Toast.LENGTH_SHORT).show();
            }
        });

        _gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "nao clica", Toast.LENGTH_LONG).show();
            }
        });
        /*_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _subject = (SubjectsDTO) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), _subject.getSubject(), Toast.LENGTH_LONG).show();
            }
        });*/

        return view;
    }

    private void InitSearchView() {
        SearchView searchView = (SearchView) getActivity().findViewById(R.id.main_action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setFocusable(true);
        searchView.requestFocus();
        searchView.setIconified(false);
        searchView.onActionViewCollapsed();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                List<SubjectsDTO> newSubjects = new ArrayList<>();

                for (SubjectsDTO s : _listSubjects) {
                    if (s.getSubject() != null && removeDiacriticalMarks(s.getSubject().toLowerCase()).contains(newText.toLowerCase()))
                        newSubjects.add(s);
                }

                _subjectsViewAdapter = new SubjectsFavoriteViewAdapter(getActivity().getApplicationContext(), R.layout.card_subjects, newSubjects);
                _gridview.setAdapter(_subjectsViewAdapter);

                return false;
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void init(View view) {
        _db = new DbOpenHelper(getActivity());
        _gridview = (GridView) view.findViewById(R.id.gv_favorites_subjects);
        _iv_favorites_material = (ImageView) view.findViewById(R.id.iv_favorites_material);
    }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    class Favorites extends AsyncTask<String, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);

            if (result > 0) {
                _subjectsViewAdapter = new SubjectsFavoriteViewAdapter(getActivity(), R.layout.tab_favorites_item, _listSubjects);
                _gridview.setAdapter(_subjectsViewAdapter);
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(String... params) {

            _dbFavorites = new DbSubjectsFavoritesHelper(_db);
            long result = 0;

            if (params[0] == "list") {
                _listSubjects = _dbFavorites.GetSubjects();
            }

            if (_listSubjects == null)
                return (long) 0;

            return (long) _listSubjects.size();
        }
    }

}
