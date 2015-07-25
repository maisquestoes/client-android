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


}
