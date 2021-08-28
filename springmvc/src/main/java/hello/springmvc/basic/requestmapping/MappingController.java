package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/hello-basic")
	public String helloBasic() {
		log.info("helloBasic");
		return "ok";
	}
	
	@RequestMapping(value = "/mapping-get-v1",method = RequestMethod.GET)
	public String mappingGetV1() {
		log.info("mapping-get-V1");
		return "ok";
	}
	
	@GetMapping("/mapping-get-v2")
	public String mappingGetV2() {
		log.info("mapping-get-V2");
		return "ok";
	}
	
	/*
	 * PathVariable 사용
	 * 변수명이 같으면 생략 가능
	 * @PathVariable("userID") String userId -> @PathVariable userId
	 * /mapping/userA
	 */
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable String userId) {
		log.info("mappingPath userID ={}", userId);
		return "ok";
	}
	
	
	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPaths(@PathVariable String userId, @PathVariable Long orderId) {
		log.info("mappingPath userID ={}, orderId = {}", userId, orderId);
		return "ok";
	}
	
	@GetMapping(value = "/mapping-param", params = "mode=debug")
	public String mappingParam() {
		return "ok";
	}
	
}
