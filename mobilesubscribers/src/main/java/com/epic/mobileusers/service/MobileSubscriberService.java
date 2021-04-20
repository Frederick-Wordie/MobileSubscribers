package com.epic.mobileusers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import com.epic.mobileusers.dto.MobileSubscriberDto;
import com.epic.mobileusers.model.MobileSubscriber;
import com.epic.mobileusers.repository.MobileSubscriberRepository;
import com.epic.mobileusers.utils.MobileSubscriberValidation;

@Service
public class MobileSubscriberService {

	@Autowired
	MobileSubscriberRepository mobileSubscriberRepository;
	
	@Autowired
	MobileSubscriberValidation mobileSubscriberValidation;
	
	public MobileSubscriber saveMobileSubscriber(MobileSubscriberDto mobileSubscriberDto) {
		return mobileSubscriberRepository.save(checkAndTransformMobileSubscriber(mobileSubscriberDto));
	}
	
	public List<MobileSubscriber> findAllMobileSubscribers(){
		return mobileSubscriberRepository.findAll();
	}
	
	public List<String> findAllMobileNumbers(){
	 return fetchMobileNumbers(findAllMobileSubscribers());
	}
	
	public List<String> findAllMobileNumbersBySearchCriteria(String search){
		List<MobileSubscriber> subscribers = mobileSubscriberRepository.findMobileSubscibersMatchingSearchCriteria(search);
		return fetchMobileNumbers(subscribers);
	}
	
	public List<MobileSubscriber> findSubscribersBySearchCriteria(String search){
		return mobileSubscriberRepository.findMobileSubscibersMatchingSearchCriteria(search);
	}
	
	public MobileSubscriber updateSubscriber(MobileSubscriberDto mobileSubscriberDto) {
		
		MobileSubscriber subscriber =	mobileSubscriberRepository.findByMsisdn(mobileSubscriberDto.getMsisdn());
		if(!ObjectUtils.isEmpty(subscriber)) {
			subscriber = mobileSubscriberDto.transformandUpdateDto(mobileSubscriberDto,subscriber);
		return 	mobileSubscriberRepository.save(subscriber);
		}else {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Mobile Subscriber does not exist");
			
		}
	}
	
	public void deleteSubscriber(String mobileNumber) {
		MobileSubscriber subscriber =	mobileSubscriberRepository.findByMsisdn(mobileNumber);
		if(!ObjectUtils.isEmpty(subscriber)) {
			mobileSubscriberRepository.delete(subscriber);
		}else {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Mobile Subscriber does not exist");
		}
	}
	
	private List<String> fetchMobileNumbers(List<MobileSubscriber> subscribers){
		List<String> mobileNumbers = subscribers.stream().map(s -> 
		s.getMsisdn()).collect(Collectors.toList());
		
		return mobileNumbers;
	}
	private MobileSubscriber checkAndTransformMobileSubscriber(MobileSubscriberDto mobileSubscriberDto) {
	
		if(!mobileSubscriberValidation.isValidMobileNumber(mobileSubscriberDto.getMsisdn())) {
			throw new IllegalArgumentException("Mobile number is not valid");
		}
		MobileSubscriber newSubscriber = new MobileSubscriber();
		MobileSubscriber subscriber =	mobileSubscriberRepository.findByMsisdn(mobileSubscriberDto.getMsisdn());
		if(subscriber == null) {
			if(mobileSubscriberDto.getCustomerIdOwner() == null) {
				
				throw new IllegalArgumentException("Customer Owner Id cannot be null");
			}
			if(mobileSubscriberDto.getCustomerIdUser() == null) {
				throw new IllegalArgumentException("Customer User Id cannot be null");
			}
			if(!mobileSubscriberValidation.isValidServiceType(mobileSubscriberDto.getServiceType())	) {
				throw new ResponseStatusException(
				           HttpStatus.NOT_FOUND, "Service Type does not exist");
			}
		}else {
			throw new IllegalArgumentException("Mobile number all ready exist");
		}
		
		return mobileSubscriberDto.transformDto(mobileSubscriberDto,newSubscriber);
	}
	
	
	
	
}
