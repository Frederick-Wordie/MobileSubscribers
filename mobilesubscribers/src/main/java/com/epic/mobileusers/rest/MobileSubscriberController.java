package com.epic.mobileusers.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epic.mobileusers.dto.ErrorModel;
import com.epic.mobileusers.dto.ErrorResponse;
import com.epic.mobileusers.dto.MobileSubscriberDto;
import com.epic.mobileusers.model.MobileSubscriber;
import com.epic.mobileusers.service.MobileSubscriberService;

@RestController
@RequestMapping("/api")
public class MobileSubscriberController {

	@Autowired
	MobileSubscriberService mobileSubscriberService;

	@GetMapping(value = "/getMobileSubscribers")
	@ResponseStatus(HttpStatus.OK)
	public List<MobileSubscriber> getMobileSubscribers() {
		return mobileSubscriberService.findAllMobileSubscribers();
	}
	
	@GetMapping(value = "/getMobileNumbers")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getMobileNumbers() {
		return mobileSubscriberService.findAllMobileNumbers();
	}
	
	@GetMapping(value = "/getMobileSubscribers/{search}")
	@ResponseStatus(HttpStatus.OK)
	public List<MobileSubscriber> getMobileSubscribersBySearch(@PathVariable String search) {
		return mobileSubscriberService.findSubscribersBySearchCriteria(search);
	}
	
	@GetMapping(value = "/getMobileNumbers/{search}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getMobileNumbers(@PathVariable String search) {
		return mobileSubscriberService.findAllMobileNumbersBySearchCriteria(search);
	}
	
	@PostMapping(value = "/saveMobileSubscriber")
	@ResponseStatus(HttpStatus.CREATED)
	public MobileSubscriber saveSubscriber(@RequestBody MobileSubscriberDto mobileSubscriberDto) {
		return mobileSubscriberService.saveMobileSubscriber(mobileSubscriberDto);
	}
	
	@PutMapping(value = "/updateMobileSubscriber")
	@ResponseStatus(HttpStatus.CREATED)
	public MobileSubscriber updateSubscriber(@RequestBody MobileSubscriberDto mobileSubscriberDto) {
		return mobileSubscriberService.updateSubscriber(mobileSubscriberDto);
	}
	
	@DeleteMapping(value="/deleteSubscriber/{mobilenumber}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSubscriber(@PathVariable String mobilenumber) {
		 mobileSubscriberService.deleteSubscriber(mobilenumber);
	}
	
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(MethodArgumentNotValidException exception) {

		List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
				.map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage())).distinct()
				.collect(Collectors.toList());
		return ErrorResponse.builder().errorMessage(errorMessages).build();
	}
	
}
