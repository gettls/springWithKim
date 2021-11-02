package hello.crud_ex1.web.schdule;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.Schedule;
import hello.crud_ex1.domain.ScheduleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	private final ScheduleRepository scheduleRepository;
	
	public String schedule(@ModelAttribute Member member, Model model){
		List<Schedule> schedule = scheduleRepository.findByMember(member);
		model.addAttribute("schedule", schedule);
		return "schedule/schedules";
	}
	
	
	
}
