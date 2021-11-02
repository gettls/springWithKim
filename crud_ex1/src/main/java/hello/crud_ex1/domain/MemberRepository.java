package hello.crud_ex1.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public void updateMember(Long id, Member updateMember) {
		Member findOne = findById(id);
		findOne.setLoginId(updateMember.getLoginId());
		findOne.setName(updateMember.getName());
		findOne.setPassword(updateMember.getPassword());
	}
	
	public void clear() {
		store.clear();
	}
}
