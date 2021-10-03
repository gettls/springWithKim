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
		Assertions.assertThat(result).isEqualTo("안녕");
	}
	
	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(()->ms.getMessage("asdasd", null, null))
			.isInstanceOf(NoSuchMessageException.class);
	}
	
	
	@Test
	void notFoundMessageCodeDefaultMessage(){
		String result = ms.getMessage("asdasd", null, "기본 메시지", null);
		assertThat(result).isEqualTo("기본 메시지");
	}
	
	@Test
	void argumentMessage() {
		String message = ms.getMessage("hello.name", new Object[] {"Spring"}, null);
		assertThat(message).isEqualTo("안녕 Spring");
	}
	// hello.name = 안녕으로 치환되고
	// {0} 에 Spring이 들어감 => 결과적으로 "안녕 Spring"
	
	@Test
	void defaultLang() {
		assertThat(ms.getMessage("hello", null,null)).isEqualTo("안녕");
		assertThat(ms.getMessage("hello", null,Locale.KOREA)).isEqualTo("안녕");
	}
	
	@Test
	void enLang() {
		assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
	}
}