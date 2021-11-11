package hello.crud_ex1.service;

import org.springframework.stereotype.Service;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
	
	private final MemberService memberService;
	
	public Member login(String loginId, String password) {
		Member member = memberService.findOnebyLoginId(loginId);
		log.info("from : {} {}", loginId, password);
		log.info("to : {} {}", member.getId(), member.getPassword());
		if(!member.getPassword().equals(password)) return null;
		return member;
	}
}
