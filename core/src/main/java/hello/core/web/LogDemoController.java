package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // 생성자 필요 없음 ! (자동으로 주입 lombok)
public class LogDemoController {
	
	// 그냥 의존객체 주입을 요청하면
	// LogDemoService는 required 스코프 빈 (value = "required")이기 때문에
	// http 요청이 없는 현 시점에서 생성되지 않은 상태임
	// 따라서 에러가 발생함
	// -> provider를 이용해서 스코프 빈 생성을 http 요청이 있을때까지 빈 요청을 미룸
//	private final LogDemoService logDemoService;
//	private final ObjectProvider<MyLogger> myLoggerProvider;
	
	private final LogDemoService logDemoService;
	private final MyLogger myLogger;
	
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		myLogger.setRequestURL(requestURL);
		
		myLogger.log("controller test");
		logDemoService.logic("testId");
		return "OK";
	}
}
