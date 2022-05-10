package kr.co.cslee.ysko;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.co.cslee.ysko.test.service.TestService;
import kr.co.cslee.ysko.test.vo.TestVo;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(
		// properties = { "testId=ysko", "testName=영" }
		// classes = {TestJpaRestController.class, MemberService.class},
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
@Slf4j
class YskoApplicationTests2 {
	private final Logger logger = LoggerFactory.getLogger(YskoApplicationTests.class);

	@Autowired
	TestService testService;

	@Autowired
	MockMvc mvc;
	@Autowired
	private WebApplicationContext ctx;

//	@BeforeEach() 
//	public void setup() {
//		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).addFilters(new CharacterEncodingFilter("UTF-8", true)) // 필터 추가, 한글 깨짐 방지
//				.alwaysDo(print()).build();
//	}

//	@Test void getMember() throws Exception { 
//		log.info("##### Properties 테스트 #####"); 
//		/******** START : MOC MVC test **********/ 
//		log.info("******** START : MOC MVC test **********"); 
//		mvc.perform(get("/memberTest/1")) 
//			.andExpect(status().isOk()) 
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
//			.andExpect(jsonPath("$.id", is("goddaehee"))) 
//			.andDo(print()); 
//		log.info("******** END : MOC MVC test **********"); 
//		/******** END : MOC MVC test **********/ 
//		
//		/******** START : TestRestTemplate test **********/ 
//		log.info("******** START : TestRestTemplate test **********"); 
//		ResponseEntity<MemberVo> response = restTemplate.getForEntity("/memberTest/1", MemberVo.class); 
//		then(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
//		then(response.getBody()).isNotNull(); 
//		log.info("******** END : TestRestTemplate test **********"); 
//		/******** END : TestRestTemplate test **********/ 
//		
//		/******** START : MockBean test **********/ 
//		log.info("******** START : MockBean test **********"); 
//		Optional<MemberVo> member = memberService.findById(1L); 
//		if (member.isPresent()) { 
//			then("goddaehee").isEqualTo(member.get().getId()); 
//			then("갓대희").isEqualTo(member.get().getName()); 
//		} 
//		log.info("******** END : MockBean test **********"); 
//		/******** END : MockBean test **********/ }
//	}

	@Test
	void connectTest() {
		List<TestVo> testList = testService.selectTest();
		assertNotNull(testList);

		for (TestVo testVo : testList) {
			logger.debug(testVo.getIdx());
			assertEquals("1", testVo.getIdx());
		}

	}

	@Value("${testId}")
	private String testId;
	@Value("${testName}")
	private String testName;

	@Test
	void getMember() throws Exception {
		logger.info("##### Properties 테스트 #####");
		logger.info("testId : " + testId);
		logger.info("testName : " + testName);
	}

	@Autowired
	private TestRestTemplate restTemplate; // webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 추가 필수
	@Test
	void getMemberRestTest() throws Exception {
		logger.info("******** START : TestRestTemplate test **********");
		ResponseEntity<String> response = restTemplate.getForEntity("/valueTest", String.class);
		then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(response.getBody()).isNotNull();
		logger.info(response.getBody());
		logger.info("******** END : TestRestTemplate test **********");
	}

}
