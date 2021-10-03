package hello.login.web.session;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import hello.login.domain.member.Member;

class SessionManagerTest {

	
	SessionManager sessionManager = new SessionManager();
	
	@Test
	void sessionTest() {
		
		
		// ���� ����
		MockHttpServletResponse response = new MockHttpServletResponse();
		Member member = new Member();
		sessionManager.createSession(member, response);
	
		// ��û�� ���� ��Ű ����
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(response.getCookies()); //mySessionId = 12131-3121-qweqwe
		
		// ���� ��ȸ
		Object result = sessionManager.getSession(request);
		assertThat(result).isEqualTo(member);
		
		// ���� ����
		sessionManager.expire(request);
		Object expired = sessionManager.getSession(request);
		assertThat(expired).isNull();
	}
	
}
