package progamaro.maisquestoes_v2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.R;
import progamaro.maisquestoes_v2.domain.Answer;
import progamaro.maisquestoes_v2.dto.AnswerDTO;

/**
 * Created by helio on 29/10/15.
 */
public class AnswerRecyler extends RecyclerView.Adapter<AnswerRecylerHolder> {

    private ArrayList<AnswerDTO> _answers;
    private Context _context;


    public AnswerRecyler(Context pContext, ArrayList<AnswerDTO> pAnswers){
        this._answers = pAnswers;
        this._context = pContext;
    }

    @Override
    public AnswerRecylerHolder onCreateViewHolder(ViewGroup pViewGroup, int pViewType) {
        View _view = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.questions_item, pViewGroup, false);

        AnswerRecylerHolder _viewHolder = new AnswerRecylerHolder(_view, _answers);

        return _viewHolder;
    }

    @Override
    public void onBindViewHolder(final AnswerRecylerHolder pHolder, int pPosition) {
        final AnswerDTO _answerItem = _answers.get(pPosition);


        pHolder._tv_question_answer.setText(_answerItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return (null != _answers ? _answers.size() : 0);
    }
}
