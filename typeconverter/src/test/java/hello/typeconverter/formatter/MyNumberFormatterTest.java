package hello.typeconverter.formatter;


import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Locale;

import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MyNumberFormatterTest {

	private final MyNumberFormatter formatter = new MyNumberFormatter(); 
	
	@Test
	void parse() throws ParseException { 
		Number result = formatter.parse("1,000", Locale.KOREA);
		assertThat(result).isEqualTo(1000L); // Long 타입 주의 -> parse의 결과가 Long 이기 때문
	}

	@Test
	void print() {
		String result = formatter.print(1000, Locale.KOREA);
		assertThat(result).isEqualTo("1,000");
	}
	
}
