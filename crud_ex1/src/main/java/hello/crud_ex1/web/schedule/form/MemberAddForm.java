package hello.crud_ex1.web.schedule.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberAddForm {
	@NotBlank
	private String name;
	@NotBlank
	private String loginId;
	@NotBlank
	private String password;
}
