package progamaro.maisquestoes_v2.domain;

/**
 * Created by andremiranda on 13/08/15.
 */
public class Subject {
    private String _id;
    private int sql_id;
    private String subject;

    public Subject() {
    }

    public Subject(String _id, int sql_id, String subject, String created) {

        this._id = _id;
        this.sql_id = sql_id;
        this.subject = subject;
        this.created = created;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getSql_id() {
        return sql_id;
    }

    public void setSql_id(int sql_id) {
        this.sql_id = sql_id;
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

    private String created;
}
