package com.devopsbuddy9;

import com.devopsbuddy9.web.i18n.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Devopsbuddy9ApplicationTests {


	@Autowired
	private I18NService i18NService;

	@Test
	public void testMessageByLocaleService() throws Exception{
		String expectedResult = "Bootstrap starter template";
		String messageId= "index.main.callout";
		String actual = i18NService.getMessage(messageId);

		Assert.assertEquals("The actual and expected Strings don't match", expectedResult, actual);
	}


}
