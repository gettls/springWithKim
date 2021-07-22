package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // ������ �ʿ� ���� ! (�ڵ����� ���� lombok)
public class LogDemoController {
	
	// �׳� ������ü ������ ��û�ϸ�
	// LogDemoService�� required ������ �� (value = "required")�̱� ������
	// http ��û�� ���� �� �������� �������� ���� ������
	// ���� ������ �߻���
	// -> provider�� �̿��ؼ� ������ �� ������ http ��û�� ���������� �� ��û�� �̷�
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
