package progamaro.maisquestoes_v2.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andremiranda on 25/07/15.
 */
public class QuestionDTO {

    public String _id;
    public String query;
    public ArrayList<AnswerDTO> answers;
    public QuestionTimeDTO time;
    public String[] questionImages;
    public String type;
    public String miss;
    public String hits;

    public QuestionDTO() {
    }

    public QuestionDTO(String _id, String query, ArrayList<AnswerDTO> answers, QuestionTimeDTO time, String[] questionImages, String type, String miss, String hits) {
        this._id = _id;
        this.query = query;
        this.answers = answers;
        this.time = time;
        this.questionImages = questionImages;
        this.type = type;
        this.miss = miss;
        this.hits = hits;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<AnswerDTO> answers) {
        this.answers = answers;
    }

    public QuestionTimeDTO getTime() {
        return time;
    }

    public void setTime(QuestionTimeDTO time) {
        this.time = time;
    }

    public String[] getQuestionImages() {
        return questionImages;
    }

    public void setQuestionImages(String[] questionImages) {
        this.questionImages = questionImages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMiss() {
        return miss;
    }

    public void setMiss(String miss) {
        this.miss = miss;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }
}
