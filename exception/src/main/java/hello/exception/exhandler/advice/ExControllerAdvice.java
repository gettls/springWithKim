package hello.exception.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResult illegalHandler(IllegalArgumentException e) {
		log.error("[exceptionHandler] ex",e);
		return new ErrorResult("BAD", e.getMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> userException(UserException e){
		log.error("[exceptionHandler] ex",e);
		ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandler(Exception e) {
		log.error("[exceptionHandler] ex", e);
		return new ErrorResult("EX", "���� ����");
	}
}
