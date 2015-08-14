package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;

/**
 * Created by andremiranda on 06/08/15.
 */
public class SubjectsViewAdapter extends BaseAdapter {

    private TextView tv_subject;
    private TextView tv_subject_description;
    private CardView cv_cardview_subject;
    private ImageView iv_icon_like;

    private List<SubjectsDTO> _subjects;
    private Context _context;

    public SubjectsViewAdapter(List<SubjectsDTO> subjects, Context c) {
        _subjects = subjects;
        _context = c;
    }

    public int getCount() {
        return _subjects.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            SubjectsDTO obj = _subjects.get(position);

            convertView = LayoutInflater.from(_context).inflate(R.layout.card_subjects, null);

            cv_cardview_subject = (CardView) convertView.findViewById(R.id.cv_subjects);
            tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
            tv_subject_description = (TextView) convertView.findViewById(R.id.tv_subject_description);
            iv_icon_like = (ImageView) convertView.findViewById(R.id.iv_icon_like);

            tv_subject.setText(obj.getSubject());
            tv_subject_description.setText(obj.getSubject());

            iv_icon_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(_context, "click no icone", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }

}
