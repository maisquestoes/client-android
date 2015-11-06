package progamaro.maisquestoes_v2.dto;

/**
 * Created by helio on 29/10/15.
 */
public class AnswerDTO {

    private String _id;
    private String description;
    private String correct;
    private Boolean active;

    public AnswerDTO() {
    }

    public AnswerDTO(String _id, String description, String correct) {
        this._id = _id;
        this.description = description;
        this.correct = correct;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
