package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("��� �� ����ϱ�")
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String beanDifinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDifinitionName);
			System.out.println("name = "+beanDifinitionName + "ojbect = "+bean);
		}
	}
	
	@Test
	@DisplayName("���ø����̼� �� ����ϱ�")
	void findAppBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String beanDifinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDifinitionName);
			
			// Role ROLE_APPLICATION : ���� ����� ���ø����̼� ��
			// Role ROLE_INFRASTRUCTURE : �������� ���ο��� ����ϴ� ��
			if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE)
			{
				Object bean = ac.getBean(beanDifinitionName);
				System.out.println("name = "+beanDifinitionName + "ojbect = "+bean);
			}
		}
	}
}
