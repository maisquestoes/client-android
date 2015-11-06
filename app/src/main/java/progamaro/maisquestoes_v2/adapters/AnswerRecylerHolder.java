package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.dto.AnswerDTO;

/**
 * Created by helio on 29/10/15.
 */
public class AnswerRecylerHolder extends RecyclerView.ViewHolder{

    public CardView _cv_question_answer;
    public TextView _tv_question_answer;
    public Context _context;
    private List<AnswerDTO> _answers;

    public AnswerRecylerHolder(View view, List<AnswerDTO> pAnswers) {
        super(view);
        this._tv_question_answer = (TextView) view.findViewById(R.id.tv_question_answer);
        this._cv_question_answer = (CardView) view.findViewById(R.id.cv_question_answer);
        this._context = view.getContext();
        this._answers = pAnswers;
        this._cv_question_answer.setClickable(true);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
