package progamaro.maisquestoes_v2.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import progamaro.maisquestoes_v2.R;

/**
 * Created by andremiranda on 05/08/15.
 */
public class SubjectsViewHolder {

    public CardView cv_cardview;
    public ImageView iv_subject;

    public LinearLayout ll_card_subjects;
    public TextView tv_subject;
    public TextView tv_subject_description;
    public ImageView iv_icon_like;


    /*public SubjectsViewHolder(View itemView) {

        //cv_cardview = (CardView) itemView.findViewById(R.id.cv_subjects);
        tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
        tv_subject_description = (TextView) itemView.findViewById(R.id.tv_subject_description);
        iv_icon_like = (ImageView) itemView.findViewById(R.id.iv_icon_like);
    }*/
}

