package hello.crud_ex1.web.login;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {
	@NotEmpty
	public String password;
	@NotEmpty
	public String loginId;
}
