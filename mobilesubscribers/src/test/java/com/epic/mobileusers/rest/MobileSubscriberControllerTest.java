package com.epic.mobileusers.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.epic.mobileusers.dto.MobileSubscriberDto;
import com.epic.mobileusers.dto.ServiceType;
import com.epic.mobileusers.model.MobileSubscriber;

public class MobileSubscriberControllerTest extends AbstractControllerTest{

	@Test
	public void shouldAddMobileSubscribers() throws Exception {
		
		//given
		String content = "{\"msisdn\": \"945433427820\",\"customerIdOwner\": 2, \"customerIdUser\": 2,\"serviceType\": \"MOBILE_POSTPAID\"}";
		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(2);
		mobileSubscriberDto.setCustomerIdUser(2);
		mobileSubscriberDto.setMsisdn("945433427820");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_POSTPAID.name());
		MobileSubscriber subscriber = mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
		
		// when
		when(mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto)).thenReturn(subscriber);

		// then
		mockMvc.perform(post("/api/saveMobileSubscriber")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void givenSubscribers_whenGetSubscribers_thenStatus200() throws Exception {
		
		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(3);
		mobileSubscriberDto.setCustomerIdUser(3);
		mobileSubscriberDto.setMsisdn("144439427822");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_POSTPAID.name());
		mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
		
		mockMvc.perform(get("/api/getMobileSubscribers")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
			     
	}
	
	@Test
	public void givenSubscribers_whenDeleteSubscriber_thenStatus200() throws Exception {
		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(3);
		mobileSubscriberDto.setCustomerIdUser(3);
		mobileSubscriberDto.setMsisdn("874439427822");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_POSTPAID.name());
		mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
		
		mockMvc.perform(delete("/api/deleteSubscriber/874439427822")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
			   		     
	}
	
	@Test
	public void shouldUpdateMobileSubscribers() throws Exception {
		
		String content = "{\"msisdn\": \"974439427822\",\"customerIdOwner\": 2, \"customerIdUser\": 2,\"serviceType\": \"MOBILE_POSTPAID\"}";

		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(3);
		mobileSubscriberDto.setCustomerIdUser(3);
		mobileSubscriberDto.setMsisdn("974439427822");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_POSTPAID.name());
		mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
		
		mockMvc.perform(put("/api/updateMobileSubscriber")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void shouldSearchMobileSubscriber() throws Exception {
		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(3);
		mobileSubscriberDto.setCustomerIdUser(3);
		mobileSubscriberDto.setMsisdn("674439427822");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_POSTPAID.name());
		mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
		
		mockMvc.perform(get("/api/getMobileSubscribers/974439427822")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
