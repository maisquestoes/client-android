package progamaro.maisquestoes_v2.domain;

/**
 * Created by helio on 25/07/15.
 */
public class ExaminingBoard {
    private String id;
    private String examiningboard;
    private String acronym;

    public ExaminingBoard() {
    }

    public ExaminingBoard(String id, String examiningboard, String acronym) {
        this.id = id;
        this.examiningboard = examiningboard;
        this.acronym = acronym;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExaminingboard() {
        return examiningboard;
    }

    public void setExaminingboard(String examiningboard) {
        this.examiningboard = examiningboard;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
