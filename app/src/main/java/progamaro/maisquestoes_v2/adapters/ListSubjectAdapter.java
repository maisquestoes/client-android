package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 14/08/15.
 */
public class ListSubjectAdapter extends ArrayAdapter<SubjectsDTO> {

    private List<SubjectsDTO> _subjects;
    private Context _context;

    public ListSubjectAdapter(Context context, int resource, List<SubjectsDTO> subjects) {
        super(context, resource, subjects);
        _subjects = subjects;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

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

        SubjectsDTO obj = _subjects.get(position);

        if(obj != null){
            _holder.tv_tab_subjects_subject.setText(obj.getSubject());
            _holder.iv_tab_subject_favorite.setSelected(obj.isChecked());
        }

        return convertView;

    }
}
