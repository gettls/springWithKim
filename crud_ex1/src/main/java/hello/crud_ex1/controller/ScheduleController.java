package hello.crud_ex1.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.MemberRepository;
import hello.crud_ex1.domain.Schedule;
import hello.crud_ex1.domain.ScheduleRepository;
import hello.crud_ex1.service.MemberService;
import hello.crud_ex1.service.ScheduleService;
import hello.crud_ex1.web.schedule.form.ScheduleAddForm;
import hello.crud_ex1.web.schedule.form.ScheduleEditForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {

	private final ScheduleService scheduleService;
	private final MemberService memberService;
	
	@GetMapping("/{memberId}")
	public String list(@PathVariable Long memberId, Model model) {
		log.info("LIST");
		log.info("memberId = {}",memberId);
		Member member = memberService.findOne(memberId);
		List<Schedule> schedules = scheduleService.findByMember(member);
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
			log.info("���ε�����");
			return "schedule/addForm";
		}
		log.info("date = {}", form.getDate());
		Member member = memberService.findOne(memberId);
		Schedule schedule = new Schedule();
		schedule.setName(form.getName());
		schedule.setDate(form.getDate());
		schedule.setMember(member);
		log.info("member = {}", schedule.getMember());
		scheduleService.add(schedule);
		return "redirect:/schedule/{memberId}";
	}
	
	@GetMapping("/{scheduleId}/edit")
	public String editForm(@PathVariable Long scheduleId, Model model) {
		log.info("editForm");
		Schedule schedule = scheduleService.findById(scheduleId);
		Member member = memberService.findOne(schedule.getMember().getId());
		model.addAttribute("schedule", schedule);
		model.addAttribute("member", member);
		log.info("memberId = {}",member.getId());
		return "schedule/editForm";
	}
	
	@PostMapping("/{scheduleId}/edit")
	public String edit(@Validated @ModelAttribute ScheduleEditForm form, 
			@PathVariable Long scheduleId, BindingResult bindingResult
			, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			log.info("���ε� ����");
			return "schedule/editForm";
		}
		Schedule schedule = scheduleService.findById(scheduleId);
		Long memberId = schedule.getMember().getId();
		schedule.setDate(form.getDate());
		schedule.setName(form.getName());
		scheduleService.update(scheduleId, schedule);
		
		redirectAttributes.addAttribute("memberId", memberId);
		
		log.info("schedule = {}", schedule);
		log.info("redirect to list");
		return "redirect:/schedule/{memberId}";
	}
	
}
