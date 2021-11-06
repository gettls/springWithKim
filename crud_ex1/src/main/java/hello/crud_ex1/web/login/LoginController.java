package hello.crud_ex1.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hello.crud_ex1.domain.LoginService;
import hello.crud_ex1.domain.Member;
import hello.crud_ex1.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	public final LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm loginForm) {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
			HttpServletRequest request) {
		log.info("�Է� ����");
		if(bindingResult.hasErrors()) {
			log.info("binding Error");
			return "login/loginForm";
		}
		Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		
		if(loginMember==null) {
			log.info("not correct");
			bindingResult.reject("���̵� Ȥ�� ��й�ȣ�� ���� �ʽ��ϴ�.");
			return "login/loginForm";
		}
		
		log.info("session ����");
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		return "redirect:/";
	}
}
