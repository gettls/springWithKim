package hello.crud_ex1.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hello.crud_ex1.domain.Schedule;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduleValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("validator");
		return Schedule.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("validator");
		Schedule schedule = (Schedule) target;
		if(!StringUtils.hasText(schedule.getName())) {
			errors.rejectValue("name", null);
		}
	}

}
