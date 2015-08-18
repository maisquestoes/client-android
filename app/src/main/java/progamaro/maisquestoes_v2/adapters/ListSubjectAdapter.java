package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;
import progamaro.maisquestoes_v2.sqlite.DbOpenHelper;
import progamaro.maisquestoes_v2.sqlite.DbSubjectsFavoritesHelper;

/**
 * Created by andremiranda on 14/08/15.
 */
public class ListSubjectAdapter extends ArrayAdapter<SubjectsDTO> {

    private List<SubjectsDTO> _subjects;
    private Context _context;
    private SubjectsDTO _currentSubject;

    public ListSubjectAdapter(Context context, int resource, List<SubjectsDTO> subjects) {
        super(context, resource, subjects);
        _subjects = subjects;
        _context = context;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ListSubjectHolder _holder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_subjects_subject, null);

            _holder = new ListSubjectHolder();
            _holder.tv_tab_subjects_subject = (TextView) convertView.findViewById(R.id.tv_tab_subjects_subject_description);
            _holder.iv_tab_subject_favorite = (ImageView) convertView.findViewById(R.id.iv_tab_subject_favorite);

            convertView.setTag(_holder);

        }else{
            _holder = (ListSubjectHolder) convertView.getTag();
        }

        final SubjectsDTO _currentSubject = _subjects.get(position);

//        _holder.iv_tab_subject_favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(_context, "You clicked image " + position, Toast.LENGTH_LONG).show();
//
//                _currentSubject.setChecked(!_holder.iv_tab_subject_favorite.isSelected());
//            }
//        });

        if(_currentSubject != null){
            _holder.tv_tab_subjects_subject.setText(_currentSubject.getSubject());
            _holder.iv_tab_subject_favorite.setSelected(_currentSubject.isChecked());
        }

        return convertView;

    }
}
