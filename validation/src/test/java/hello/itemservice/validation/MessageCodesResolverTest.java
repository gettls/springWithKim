package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

public class MessageCodesResolverTest {
	
	MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
	
	@Test
	void messageCodesResolverObject() {
		String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
		Assertions.assertThat(messageCodes).containsExactly("requried.item", "required");
	}
	
	@Test
	void messageCodeResolverField() {
		String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
	}
	
}
