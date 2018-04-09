package com.shangfu.acvitity.qingming;

import com.shangfu.acvitity.qingming.service.MemberRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QingmingApplicationTests {
	@Autowired
	private MemberRecordService memberRecordService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findInfo(){
//		Map<String,String> multiValueMap = new HashMap<>();
		String result = testRestTemplate.postForObject("/Qingming/findInfo", null, String.class);
		System.out.println("result = " + result);
	}

}
