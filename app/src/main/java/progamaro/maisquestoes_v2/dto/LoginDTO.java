package progamaro.maisquestoes_v2.dto;

import progamaro.maisquestoes_v2.domain.User;

/**
 * Created by helio on 26/07/15.
 */
public class LoginDTO {

    private User userLogin;

    public User getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(User userLogin) {
        this.userLogin = userLogin;
    }
}
