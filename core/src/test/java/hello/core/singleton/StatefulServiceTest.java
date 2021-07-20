package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		// ThreadA: A����� �ֹ�
		statefulService1.order("userA", 10000);
		// ThreadB: B����� �ֹ�
		statefulService1.order("userB", 20000);
	
		// ThreadA: A����� �ֹ� �ݾ� ��ȸ
		int price = statefulService1.getPrice(); // 20000�� ����
		
		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
		// => ����
	}

	static class TestConfig{
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}