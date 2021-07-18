package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String beanDifinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDifinitionName);
			System.out.println("name = "+beanDifinitionName + "ojbect = "+bean);
		}
	}
	
	@Test
	@DisplayName("어플리케이션 빈 출력하기")
	void findAppBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String beanDifinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDifinitionName);
			
			// Role ROLE_APPLICATION : 직접 등록한 어플리케이션 빈
			// Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE)
			{
				Object bean = ac.getBean(beanDifinitionName);
				System.out.println("name = "+beanDifinitionName + "ojbect = "+bean);
			}
		}
	}
}
