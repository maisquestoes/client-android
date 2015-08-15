package progamaro.maisquestoes_v2.dto;

/**
 * Created by andremiranda on 05/08/15.
 */
public class SubjectsDTO {
    private String _id;
    private String created;
    private String subject;
    private boolean checked;

    public SubjectsDTO() {
    }

    public SubjectsDTO(String _id, String created, String subject_descrpiton) {
        this._id = _id;
        this.created = created;
        this.subject = subject_descrpiton;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
