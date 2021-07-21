package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

public class AutowiredTest {

	@Test
	void AutowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
		
	}
	
	static class TestBean{
		
		// Member 클래스는 컨테이너에 등록된 스프링 빈이 아님
		// => 주입할 대상이 없는 경우
		@Autowired(required = false)
		public void setNoBean1(Member member) {
			System.out.println("noBean1 = " + member);
		}
		
		@Autowired
		public void setNoBean2(@Nullable Member member) {
			System.out.println("noBean2 = " + member);
		}
		
		@Autowired
		public void setNoBean3(Optional<Member> member) {
			System.out.println("noBean3 = " + member);
		}
		
		
	}
	
}
