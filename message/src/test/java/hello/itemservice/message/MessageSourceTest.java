package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

	@Autowired
	MessageSource ms;
	
	@Test
	void helloMessage() {
		String result = ms.getMessage("hello", null, null);
		Assertions.assertThat(result).isEqualTo("�ȳ�");
	}
	
	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(()->ms.getMessage("asdasd", null, null))
			.isInstanceOf(NoSuchMessageException.class);
	}
	
	
	@Test
	void notFoundMessageCodeDefaultMessage(){
		String result = ms.getMessage("asdasd", null, "�⺻ �޽���", null);
		assertThat(result).isEqualTo("�⺻ �޽���");
	}
	
	@Test
	void argumentMessage() {
		String message = ms.getMessage("hello.name", new Object[] {"Spring"}, null);
		assertThat(message).isEqualTo("�ȳ� Spring");
	}
	// hello.name = �ȳ����� ġȯ�ǰ�
	// {0} �� Spring�� �� => ��������� "�ȳ� Spring"
	
	@Test
	void defaultLang() {
		assertThat(ms.getMessage("hello", null,null)).isEqualTo("�ȳ�");
		assertThat(ms.getMessage("hello", null,Locale.KOREA)).isEqualTo("�ȳ�");
	}
	
	@Test
	void enLang() {
		assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
	}
}