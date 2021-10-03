package hello.login.web.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SessionManager {
	
	public static final String SESSION_COOKIE_NAME = "mySessionId"; 
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();
	
	/*
	 * ���� ����
	 * sessionId ����
	 * ���� ����ҿ� sessionId�� ������ �� ����
	 * sessionId�� ���� ��Ű�� ������ Ŭ���̾�Ʈ�� ����
	 */
	public void createSession(Object value, HttpServletResponse response) {
		
		// ���� id �����ϰ� ���� ���ǿ� ����
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, value);
		
		// ��Ű ����
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);  
		response.addCookie(mySessionCookie);
	}
	
	/*
	 * ���� ��ȸ
	 */
	public Object getSession(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie == null) {
			return null;
		}
		return sessionStore.get(sessionCookie.getValue());
	}

	/*
	 * ���� ����
	 */
	public void expire(HttpServletRequest request) {
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie!=null) {
			sessionStore.remove(sessionCookie.getValue());
		}
	}
	
		private Cookie findCookie(HttpServletRequest request, String cookieName) {
//		Cookie[] cookies = request.getCookies();
		if(request.getCookies() == null) {
			return null;
		}
//		for(Cookie cookie : cookies) {
//			if(cookie.getName().equals(SESSION_COOKIE_NAME)) {
//				return sessionStore.get(cookie.getValue());
//			}
//		}
		return Arrays.stream(request.getCookies())
				.filter(cookie->cookie.getName().equals(cookieName))
				.findAny()
				.orElse(null);		
	}
	
}
