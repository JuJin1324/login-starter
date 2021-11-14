package practice.login.sessionstarter.web.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/11/10
 */

@Data
public class LoginForm {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}
