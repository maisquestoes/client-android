package progamaro.maisquestoes_v2.dto;

/**
 * Created by andremiranda on 25/07/15.
 */
public class QuestionDTO {
    public String headerText;
    public String questionText;
    public String[] questionAswers;
    public String[] questionImages;
    public String subject;
    public String[] subjectTags;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getSubjectTags() {
        return subjectTags;
    }

    public void setSubjectTags(String[] subjectTags) {
        this.subjectTags = subjectTags;
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

    public String[] getQuestionImages() {
        return questionImages;
    }

    public void setQuestionImages(String[] questionImages) {
        this.questionImages = questionImages;
    }
}
