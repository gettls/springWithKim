package hello.crud_ex1.domain;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Schedule {

	private Long id;
	private Member member;
	private String name;
	private SimpleDateFormat date;
	
	public Schedule(String name, SimpleDateFormat date) {
		this.name = name;
		this.date = date;
	}

	public Schedule() {
	}
	
}
