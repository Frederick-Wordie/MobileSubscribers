package com.epic.mobileusers.rest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.epic.mobileusers.service.MobileSubscriberService;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	
	@MockBean
	protected MobileSubscriberService mobileSubscriberService;
	
	@MockBean
	protected MobileSubscriberController mobileSubscriberController;
	

	
	@Before
	public void setUp() {
		Mockito.reset(mobileSubscriberService,mobileSubscriberController);
	}
}
