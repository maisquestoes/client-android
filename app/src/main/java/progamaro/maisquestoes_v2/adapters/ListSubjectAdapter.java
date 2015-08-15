package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private TextView tv_tab_subjects_subject_description;

    public ListSubjectAdapter(Context context, int resource, List<SubjectsDTO> subjects) {
        super(context, resource, subjects);
        _subjects = subjects;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_subjects_subject, null);
            tv_tab_subjects_subject_description = (TextView) convertView.findViewById(R.id.tv_tab_subjects_subject_description);


        }

        return convertView;

    }
}
