package com.shangfu.acvitity.qingming;

import com.shangfu.acvitity.qingming.entity.JacksonUtil;
import com.shangfu.acvitity.qingming.entity.MD5;
import com.shangfu.acvitity.qingming.service.MemberRecordService;
import com.shangfu.acvitity.qingming.service.impl.MemberRecordServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	/*@Test*/
	/*public void findInfo() throws IOException {
		String mobilePhone="12345678977";
		String key = "5a9e1ff5f75898f7a1351ad361f29047";
		String keyWord="qingmignbh8sf";
		*//*String keyWord = "jaixiajdfdhfhvsf50";*//*
		RestTemplate client = new RestTemplate();
		String signPain = new StringBuffer().append(mobilePhone.toString()).
				append(keyWord.toString()).append(key).toString();

		String sign= MD5.MD5Encode(signPain);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mobilePhone",mobilePhone);
		map.put("keyWord",keyWord);
		map.put("sign",sign);
        String url = "http://1926p125y7.iok.la/shangfu-mfjc-api/api/memberCoupon/sendMemberCoupon";

        String param = "key=l!4rd2HEa7$GVDrQ" + "&mobilePhone=" + mobilePhone + "&keyWord="
                + keyWord + "&sign=" + sign;



        String json="param {\"mobilePhone\":"+mobilePhone+",\"keyWord\":"+keyWord+",\"sign\":"+sign+"}";
        System.out.println(json);
		MemberRecordServiceImpl a =new MemberRecordServiceImpl();
        String post = a.post(url, map);
        System.out.println(post);
        *//*Map maps = (Map)JSON.parse(c);
        System.out.println("这个是用JSON类来解析JSON字符串!!!");
        for (Object mapc : maps.entrySet()){
            System.out.println(((Map.Entry)mapc).getKey()+"     " + ((Map.Entry)mapc).getValue());
        }*//*

    }*/

	/*@Test
	public void aa() throws Exception {
        Date a =new Date();

        System.out.println(a.getTime());
	}*/

}
