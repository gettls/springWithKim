package hello.crud_ex1.web.members;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.MemberRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/add")
	public String member() {
		return "members/addForm"; 
	}
	
	@PostMapping("/add")
	public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "members/addForm";
		}
		memberRepository.save(member);
		return "redirect:/";
	}
}
