package progamaro.maisquestoes_v2.dto;

/**
 * Created by andremiranda on 05/08/15.
 */
public class SubjectsDTO {
    private String subject;
    private String subject_descrpiton;

    public SubjectsDTO() {
    }

    public SubjectsDTO(String subject, String subject_descrpiton) {

        this.subject = subject;
        this.subject_descrpiton = subject_descrpiton;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject_descrpiton() {
        return subject_descrpiton;
    }

    public void setSubject_descrpiton(String subject_descrpiton) {
        this.subject_descrpiton = subject_descrpiton;
    }
}
