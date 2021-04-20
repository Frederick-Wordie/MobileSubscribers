package com.epic.mobileusers.dto;

import java.time.ZonedDateTime;

import com.epic.mobileusers.model.MobileSubscriber;

import lombok.Data;

@Data
public class MobileSubscriberDto {

	private String msisdn;
	private Integer customerIdOwner;
	private Integer customerIdUser;
	private String serviceType;

	public MobileSubscriber transformDto(MobileSubscriberDto mobileSubscriberDto, MobileSubscriber subscriber) {

		subscriber.setCustomerIdOwner(mobileSubscriberDto.getCustomerIdOwner());
		subscriber.setCustomerIdUser(mobileSubscriberDto.getCustomerIdUser());
		subscriber.setMsisdn(mobileSubscriberDto.getMsisdn());
		subscriber.setServiceType(mobileSubscriberDto.getServiceType());
		subscriber.setServiceStartDate(ZonedDateTime.now());

		return subscriber;
	}
	
	public MobileSubscriber transformandUpdateDto(MobileSubscriberDto mobileSubscriberDto, MobileSubscriber subscriber) {

		subscriber.setCustomerIdOwner(mobileSubscriberDto.getCustomerIdOwner());
		subscriber.setCustomerIdUser(mobileSubscriberDto.getCustomerIdUser());
		subscriber.setServiceType(mobileSubscriberDto.getServiceType());
		subscriber.setServiceStartDate(ZonedDateTime.now());

		return subscriber;
	}

}
