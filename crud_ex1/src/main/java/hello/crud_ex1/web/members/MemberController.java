package hello.crud_ex1.web.members;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository memberRepository;
	
	@GetMapping("/members/add")
	public String register() {
		return "members/memberRegister";
	}
	
	@PostMapping("/members/add")
	public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "members/memberRegister";
		}
		memberRepository.save(member);
		return "redirect:/";
	}
}
