package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        return _subjects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.card_subjects, parent, false);

            //v = LayoutInflater.from(_context).inflate(R.layout.card_subjects, null);
        } else {
            v = (View)convertView;
        }

        SubjectsDTO obj = _subjects.get(position);

        //cv_cardview_subject = (CardView) v.findViewById(R.id.cv_subjects);
        tv_subject = (TextView) v.findViewById(R.id.tv_subject);
        tv_subject_description = (TextView) v.findViewById(R.id.tv_subject_description);
        iv_icon_like = (ImageView) v.findViewById(R.id.iv_icon_like);

        tv_subject.setText(obj.getSubject());
        tv_subject_description.setText(obj.getSubject());

        /*cv_cardview_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                *//*if (v.isSelected()){
                    iv_icon_like.setSelected(true);
                    Toast.makeText(_context, "Unselect", Toast.LENGTH_SHORT).show();
                } else {
                    iv_icon_like.setSelected(false);
                    Toast.makeText(_context, "Select", Toast.LENGTH_SHORT).show();
                }*//*
            }
        });*/

        return v;
    }

}
