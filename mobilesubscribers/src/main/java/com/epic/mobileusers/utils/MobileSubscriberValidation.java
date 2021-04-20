package com.epic.mobileusers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.epic.mobileusers.dto.ServiceType;

@Component
public class MobileSubscriberValidation {

	public Boolean isValidMobileNumber(String mobileNumber) {
		String regex = "^\\+?\\d{10,14}$";
				//"^\\+(?:[0-9]?){6,14}[0-9]$";
		//
		if (mobileNumber == null) {
            return false;
        }
		 Pattern p = Pattern.compile(regex);
		 Matcher m = p.matcher(mobileNumber);
		 return m.matches();
	}
	
	public Boolean isValidServiceType(String serviceType) {
		if(serviceType == null) {
			return false;
		}else if(serviceType.equalsIgnoreCase(ServiceType.MOBILE_PREPAID.name()) || 
					serviceType.equalsIgnoreCase(ServiceType.MOBILE_POSTPAID.name())){
			return true;
		}
		return false;
	}
}
