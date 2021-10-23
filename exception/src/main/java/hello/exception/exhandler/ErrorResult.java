package hello.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ErrorResult {
	private String code;
	private String message;
}
