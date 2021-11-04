package hello.crud_ex1.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hello.crud_ex1.domain.LoginService;
import hello.crud_ex1.domain.Member;
import hello.crud_ex1.web.SessionConst;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	public final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
			HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		
		if(loginMember==null) {
			bindingResult.reject("아이디 혹은 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		return "redirect:/";
	}
}
