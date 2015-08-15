package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 06/08/15.
 */
public class SubjectsViewAdapter extends ArrayAdapter<SubjectsDTO> {

    //private CardView cv_cardview_subject;

    private List<SubjectsDTO> _subjects;
    private Context _context;

    public SubjectsViewAdapter(Context context, int textViewResourceId, List<SubjectsDTO> pSubjectsDTO) {
        super(context,textViewResourceId,pSubjectsDTO);
        _subjects = pSubjectsDTO;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final SubjectsViewHolder _holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card_subjects, null);
            _holder = new SubjectsViewHolder();
            _holder.ll_card_subjects = (LinearLayout) convertView.findViewById(R.id.ll_card_subjects);
            /*_holder.ll_card_subjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //_holder.iv_icon_like.setSelected(!_holder.iv_icon_like.isSelected());
                    if (_holder.iv_icon_like.isSelected()) {
                        //_holder.iv_icon_like.setSelected(false);
                        _holder.tv_subject.setText("Não marcado");
                    } else {
                        //_holder.iv_icon_like.setSelected(true);
                        _holder.tv_subject.setText("Marcado");
                    }
                }
            });*/
            _holder.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
            _holder.tv_subject_description = (TextView) convertView.findViewById(R.id.tv_subject_description);
            _holder.iv_icon_like = (ImageView) convertView.findViewById(R.id.iv_icon_like);
            convertView.setTag(_holder);
        } else {
            _holder = (SubjectsViewHolder) convertView.getTag();
        }

        SubjectsDTO obj = _subjects.get(position);

        if (obj != null) {
            _holder.tv_subject.setText(obj.getSubject());
            _holder.tv_subject_description.setText(obj.getSubject());
            _holder.iv_icon_like.setSelected(obj.isChecked());
        }

        return convertView;
    }

}
