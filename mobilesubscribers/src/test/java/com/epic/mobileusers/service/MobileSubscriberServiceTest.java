package com.epic.mobileusers.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epic.mobileusers.dto.MobileSubscriberDto;
import com.epic.mobileusers.dto.ServiceType;
import com.epic.mobileusers.model.MobileSubscriber;
import com.epic.mobileusers.repository.MobileSubscriberRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileSubscriberServiceTest {

	@Autowired
	MobileSubscriberRepository mobileSubscriberRepository;
	@Autowired
	MobileSubscriberService mobileSubscriberService;
	
	@Test
	public void shouldReturnCreatedMobileSubscriber() {
		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(1);
		mobileSubscriberDto.setCustomerIdUser(1);
		mobileSubscriberDto.setMsisdn("+345233457876");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_POSTPAID.name());
		MobileSubscriber mobileSubscriber = mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
		
		assertNotNull("MobileSubscriber shouldn't be null", mobileSubscriber);
		assertThat(mobileSubscriber.getMsisdn(), equalTo("+345233457876"));
		assertThat(mobileSubscriber.getServiceType(), equalTo(ServiceType.MOBILE_POSTPAID.name()));
		assertThat(mobileSubscriber.getCustomerIdOwner(), equalTo(1));
		assertThat(mobileSubscriber.getCustomerIdUser(), equalTo(1));
		
		
		
	}
	
	@Test
	public void shouldReturnMobileSubscribers() {
		List<MobileSubscriber> subscribers = mobileSubscriberService.findAllMobileSubscribers();	
		assertTrue(subscribers.size()  > 0 ); 
	}
	
	@Test
	public void shouldReturnMobileNumbers() {
		List<String> mobileNumbers = mobileSubscriberService.findAllMobileNumbers();
		assertTrue(mobileNumbers.size()  > 0 ); 
	}
	
	@Test
	public void shouldUpdateServiceType() {
		MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto();
		mobileSubscriberDto.setCustomerIdOwner(1);
		mobileSubscriberDto.setCustomerIdUser(1);
		mobileSubscriberDto.setMsisdn("+345233457876");
		mobileSubscriberDto.setServiceType(ServiceType.MOBILE_PREPAID.name());
		MobileSubscriber mobileSubscriber = mobileSubscriberService.updateSubscriber(mobileSubscriberDto);
		assertThat(mobileSubscriber.getServiceType(), equalTo(ServiceType.MOBILE_PREPAID.name()));
	}
	
	@Test
	public void shouldReturnSubscribersBySearchParameter() {
		List<MobileSubscriber> mobileSubscribers = mobileSubscriberService.findSubscribersBySearchCriteria("PREPAID");
		assertThat(mobileSubscribers.get(0).getServiceType(), equalTo(ServiceType.MOBILE_PREPAID.name()));	
	}
	
	@Test
	public void shouldNotReturnDeletedSubscriber() {
		mobileSubscriberService.deleteSubscriber("+345233457876");
		List<String> subscribers = mobileSubscriberService.findAllMobileNumbers();	
		assertTrue(!subscribers.contains("+345233457876")); 
	}
	
	
}
