package hello.login.domain.login;

import java.util.Optional;

import org.springframework.stereotype.Service;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;
	
	/*
	 * @return null 이면 실패
	 */
	public Member login(String LoginId, String password) {
//		Optional<Member> findMemberOptional = memberRepository.findByLoginId(LoginId);
//		Member member = findMemberOptional.get();
//		if(member.getPassword().equals(password)) {
//			return member;
//		} else {
//			return null;
//		}
		return memberRepository.findByLoginId(LoginId)
				.filter(m->m.getPassword().equals(password))
				.orElse(null);
	}
	
	
}
