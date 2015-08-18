package progamaro.maisquestoes_v2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_favorities,null);

        _gridview = (GridView) view.findViewById(R.id.gv_favorites_subjects);
        init();

        new Favorites().execute("list");

        return view;
    }

    private void init() {
        _db = new DbOpenHelper(getActivity());
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
                return (long)0;

            return (long)_listSubjects.size();
        }
    }

}
