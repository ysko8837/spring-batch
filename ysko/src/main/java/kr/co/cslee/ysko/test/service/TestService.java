package kr.co.cslee.ysko.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.cslee.ysko.test.mapper.TestMapper;
import kr.co.cslee.ysko.test.vo.TestVo;

@Service
public class TestService {
	@Autowired
	public TestMapper mapper;

	public List<TestVo> selectTest() {
		return mapper.selectTest();
	}
}
