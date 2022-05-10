package kr.co.cslee.ysko.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cslee.ysko.test.service.TestService;
import kr.co.cslee.ysko.test.vo.TestVo;

@Controller
public class TestController {

	private final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/home")
	public String home() {
		return "index.html";
	}

	@ResponseBody
	@RequestMapping("/valueTest")
	public String valueTest() {
		String value = "테스트 String";
		return value;
	}

	@RequestMapping("/test")
	public ModelAndView test() throws Exception { // jsp test
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("name", "ysko");
		List<String> testList = new ArrayList<String>();
		testList.add("a");
		testList.add("b");
		testList.add("c");
		mav.addObject("list", testList);
		return mav;
	}

	@RequestMapping("/thymeleafTest")
	public String thymeleafTest(Model model) { // thymeleaf test
		System.out.println("test");
		TestVo testModel = new TestVo("ysko", "tester");
		model.addAttribute("testModel", testModel);
		return "thymeleaf/thymeleafTest";
	}

	@Autowired
	TestService testService;

	@RequestMapping(value = "/test2")
	public ModelAndView testSelect() throws Exception { //DB 연결 테스트
		
		logger.debug("db connection test - debug");
		logger.info("db connection test - info");
		logger.error("db connection test - error");
		ModelAndView mav = new ModelAndView("test2");
		List<TestVo> testList = testService.selectTest();
		mav.addObject("list", testList);
		return mav;
	}

}
