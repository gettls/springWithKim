package hello.crud_ex1.web.schedule;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.MemberRepository;
import hello.crud_ex1.domain.Schedule;
import hello.crud_ex1.domain.ScheduleRepository;
import hello.crud_ex1.web.schedule.form.ScheduleAddForm;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
	private final ScheduleRepository scheduleRepository;
	private final MemberRepository memberRepository;
	@GetMapping("/{memberId}")
	public String list(@PathVariable Long memberId, Model model) {
		List<Schedule> schedules = scheduleRepository.findAll();
		model.addAttribute("schedule", schedules);
		return "schedule/list";
	}
	
	@PostMapping("/{scheduleId}/add")
	public String add(@Validated @ModelAttribute ScheduleAddForm form, @PathVariable Long scheduleId 
					,Long memberId, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "schedule/addForm";
		}
		
		Member member = memberRepository.findById(memberId);
		Schedule schedule = new Schedule();
		schedule.setName(form.getName());
		schedule.setDate(form.getDate());
		schedule.setMember(member);
		
		return "redirect:/";
	}
}
