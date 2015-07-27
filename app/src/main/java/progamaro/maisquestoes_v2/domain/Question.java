package progamaro.maisquestoes_v2.domain;

/**
 * Created by andremiranda on 25/07/15.
 */
public class Question {
    private String id;
    private String query;
    private String text;
    private Answer aswers;
    private String[] subjects;
    private String[] roles;
    private enum type {
        objective, subjective
    }
    private Role role;

    public Question() {
    }

    public Question(String id, String query, String text, Answer aswers, String[] subjects, String[] roles, Role role) {
        this.id = id;
        this.query = query;
        this.text = text;
        this.aswers = aswers;
        this.subjects = subjects;
        this.roles = roles;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Answer getAswers() {
        return aswers;
    }

    public void setAswers(Answer aswers) {
        this.aswers = aswers;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
