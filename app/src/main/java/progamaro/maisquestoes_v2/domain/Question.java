package progamaro.maisquestoes_v2.domain;

/**
 * Created by andremiranda on 25/07/15.
 */
public class Question {
    public String headerText;
    public String questionText;
    public String[] questionAswers;
    public String[] questionImages;

    public String[] getQuestionImages() {
        return questionImages;
    }

    public void setQuestionImages(String[] questionImages) {
        this.questionImages = questionImages;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getQuestionAswers() {
        return questionAswers;
    }

    public void setQuestionAswers(String[] questionAswers) {
        this.questionAswers = questionAswers;
    }
}
