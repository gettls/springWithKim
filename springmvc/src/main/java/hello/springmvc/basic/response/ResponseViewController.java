package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

	@RequestMapping("/response-view-v1")
	public ModelAndView responseView1() {
		ModelAndView mv = new ModelAndView("response/hello")
				.addObject("data", "hello!");
		return mv;
	}
	
	@RequestMapping("/response-view-v2")
	public String responseView2(Model model) {
		model.addAttribute("data","hello!");
		return "response/hello";
	}
	
	// !! 절대 권장하지 않는 방식 !!
	@RequestMapping("/response/hello")
	public void responseView3(Model model) {
		model.addAttribute("data","hello!");
	}
	
}
