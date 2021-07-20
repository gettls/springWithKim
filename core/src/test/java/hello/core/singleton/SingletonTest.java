package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {
	
	@Test
	@DisplayName("������ ���� ������ DI �����̳�")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		// 1. ��ȸ : ȣ���� �� ���� ��ü ����
		MemberService memberService1 = appConfig.memberService();
		// 2. ��ȸ : ȣ���� �� ���� ��ü ����
		MemberService memberService2 = appConfig.memberService();
		
		// �������� �ٸ��� Ȯ��
		System.out.println("memberService1 = "+memberService1);
		System.out.println("memberService1 = "+memberService2);
		
		// memberService1 != memberService2
		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
	}
	
	@Test
	@DisplayName("�̱��� ������ ������ ��ü ���")
	void singletonServiceTest() {
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();
		
		System.out.println("singletonService1 =" + singletonService1);
		System.out.println("singletonService2 =" + singletonService2);
	}
	
	@Test
	@DisplayName("������ �����̳ʿ� �̱���")
	void springContainer() {
//		AppConfig appConfig = new AppConfig();
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// 1. ��ȸ : ȣ���� �� ���� ��ü ����
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		// 2. ��ȸ : ȣ���� �� ���� ��ü ����
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);
		// �������� ������ Ȯ��
		System.out.println("memberService1 = "+memberService1);
		System.out.println("memberService1 = "+memberService2);
		
		// memberService1 == memberService2
		Assertions.assertThat(memberService1).isSameAs(memberService2);
	}
}
