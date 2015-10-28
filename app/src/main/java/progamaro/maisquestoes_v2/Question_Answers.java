package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.domain.Answer;
import progamaro.maisquestoes_v2.domain.Question;

/**
 * Created by helio on 27/10/15.
 */
public class Question_Answers extends Activity {

    private RecyclerView _rv_questions_answers;
    private Question _question;
    private List<Answer> _answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.questions);
        init();
        initData();

    }

    private void initData() {
        _question = new Question();
        /*this.id = id;
        this.query = query;
        this.text = text;
        this.aswers = aswers;
        this.subjects = subjects;
        this.roles = roles;
        this.role = role;*/

        _answers = new ArrayList<Answer>();
        _answers.add(new Answer("553ced28396602a5602d3a8f","dúvida.",false, "pathImage"));
        _answers.add(new Answer("553ced28396602a5602d3a90","condição.",false, "pathImage"));
        _answers.add(new Answer("553ced28396602a5602d3a91","possibilidade.",false, "pathImage"));
        _answers.add(new Answer("553ced28396602a5602d3a92","ordem.",true, "pathImage"));

        _question.setId("");
        _question.setQuery("“Vá num pé e volte no outro”.Os verbos da frase acima estão empregados com o sentido de:");
        _question.setText("");
        _question.setAswers(_answers);
        _question.setSubjects(new String[]{"Português"});
    }

    private void init() {
        _rv_questions_answers = (RecyclerView) findViewById(R.id.rv_questions_answers);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
