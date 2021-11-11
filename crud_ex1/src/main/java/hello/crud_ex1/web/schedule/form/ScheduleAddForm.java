package hello.crud_ex1.web.schedule.form;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ScheduleAddForm {
	@NotNull
	public String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") /* 몰랐던 사실 : 입력되는 String 과 pattern이 같아야 한다. */
	public LocalDateTime date;
}
