package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import progamaro.maisquestoes_v2.QuestionActivity;
import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 06/08/15.
 */
public class SubjectsFavoriteViewAdapter extends ArrayAdapter<SubjectsDTO> {

    //private CardView cv_cardview_subject;

    private List<SubjectsDTO> _subjects;
    private Context _context;

    public SubjectsFavoriteViewAdapter(Context context, int textViewResourceId, List<SubjectsDTO> pSubjectsDTO) {
        super(context,textViewResourceId,pSubjectsDTO);
        _subjects = pSubjectsDTO;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final SubjectsFavoriteViewHolder _holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tab_favorites_item, null);
            _holder = new SubjectsFavoriteViewHolder();
            _holder.ll_favorite_card_subjects = (LinearLayout) convertView.findViewById(R.id.ll_favorite_card_subjects);
            _holder.tv_favorite_subject = (TextView) convertView.findViewById(R.id.tv_favorite_subject);
            _holder.tv_favorite_subject_description = (TextView) convertView.findViewById(R.id.tv_favorite_subject_description);
            convertView.setTag(_holder);
        } else {
            _holder = (SubjectsFavoriteViewHolder) convertView.getTag();
        }

        SubjectsDTO obj = _subjects.get(position);

        if (obj != null) {
            _holder.tv_favorite_subject.setText(obj.getSubject());
            _holder.tv_favorite_subject_description.setText(obj.getSubject());

            _holder.ll_favorite_card_subjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _context.startActivity(new Intent(_context, QuestionActivity.class));
                }
            });
        }

        return convertView;
    }

}
