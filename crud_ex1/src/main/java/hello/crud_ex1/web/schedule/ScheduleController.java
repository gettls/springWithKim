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
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {
	private final ScheduleRepository scheduleRepository;
	private final MemberRepository memberRepository;
	@GetMapping("/{memberId}")
	public String list(@PathVariable Long memberId, Model model) {
		Member member = memberRepository.findById(memberId);
		List<Schedule> schedules = scheduleRepository.findByMember(member);
		model.addAttribute("schedules", schedules);
		model.addAttribute("member",member);
		return "schedule/list";
	}
	
	@GetMapping("/{memberId}/add")
	public String addForm(@PathVariable Long memberId, Model model) {
		Schedule schedule = new Schedule();
		model.addAttribute("schedule",schedule);
		return "schedule/addForm";
	}
	
	@PostMapping("/{memberId}/add")
	public String add(@Validated @ModelAttribute ScheduleAddForm form, @PathVariable Long memberId,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			log.info("바인딩에러");
			return "schedule/addForm";
		}
		log.info("입력 완료");
		Member member = memberRepository.findById(memberId);
		Schedule schedule = new Schedule();
		schedule.setName(form.getName());
		schedule.setDate(form.getDate());
		schedule.setMember(member);
		scheduleRepository.save(schedule, member);
		log.info("schedule = {}",schedule);
		return "redirect:/schedule/{memberId}";
	}
}
