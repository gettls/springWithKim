package hello.crud_ex1.web.schedule.form;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ScheduleAddForm {
	@NotNull
	public String name;
	@NotNull
	public SimpleDateFormat date;
}
