package hello.crud_ex1.web.schedule.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ScheduleAddForm {
	@NotBlank
	public String name;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") /* ������ ��� : �ԷµǴ� String �� pattern�� ���ƾ� �Ѵ�. */
	public LocalDateTime date;
}
