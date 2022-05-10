package kr.co.cslee.ysko;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import kr.co.cslee.ysko.test.service.TestService;
import kr.co.cslee.ysko.test.vo.TestVo;

@SpringBootTest
class YskoApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(YskoApplicationTests.class);

	@Autowired
	TestService testService;

	@Test
	void connectTest() {
		List<TestVo> testList = testService.selectTest();
		assertNotNull(testList);

		for (TestVo testVo : testList) {
			logger.debug(testVo.getIdx());
			assertEquals("1", testVo.getIdx());
		}
	}
	
	@Value("${test.id}")
	private String ymlId;
	@Value("${test.name}")
	private String ymlName;
	@Value("${test.arrStr}")
	private List<String> arrStr;
	
	@Value("${testId}")
	private String testId;
	@Value("${testName}")
	private String testName;
	

	@Test
	void getMember() throws Exception {
		logger.info("##### Properties 테스트 #####");
		logger.info("ymlId : " + ymlId);
		logger.info("ymlName : " + ymlName);
		logger.info("testId : " + testId);
		logger.info("testName : " + testName);
		
		for (Object object : arrStr) {
			logger.info("arrStr : " + object);
		}
	}

}
