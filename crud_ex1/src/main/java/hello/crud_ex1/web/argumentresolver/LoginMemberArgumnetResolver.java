package hello.crud_ex1.web.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.web.SessionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberArgumnetResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("argumentResolver support");
		
		boolean annotation = parameter.hasParameterAnnotation(Login.class);
		boolean type = Member.class.isAssignableFrom(parameter.getParameterType());
		return annotation && type;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		log.info("argumentResolver resolove");
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		
		return request.getAttribute(SessionConst.LOGIN_MEMBER);
	}

	
}
