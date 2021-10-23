package hello.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hello.exception.api.ApiExceptionController.MemberDto;
import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
	
	@GetMapping("/api2/members/{id}")
	public MemberDto getMember(@PathVariable("id") String id) {
		if(id.equals("ex")) {
			throw new RuntimeException("�߸��� �����");
		}
		if(id.equals("bad")) {
			throw new IllegalArgumentException("�߸��� �Է� ��");
		}
		return new MemberDto(id, "hello "+id);
	}

	@GetMapping("/api2/response-status-ex1")
	public String responseStatusEx1() {
		throw new BadRequestException();
	}
	
	@GetMapping("/api2/response-status-ex2")
	public String responseStatusEx2() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());		
	}
	
	@GetMapping("/api2/default-handler-ex")
	public String defaultException(@RequestParam Integer data) {
		return "Ok";
	}
	
	@Data
	@AllArgsConstructor
	static class MemberDto{
		private String memberId;
		private String name;
	}
}
