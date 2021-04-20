package com.epic.mobileusers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epic.mobileusers.model.MobileSubscriber;

@Repository
public interface MobileSubscriberRepository extends JpaRepository<MobileSubscriber, Integer>{

	@Query("select m from MobileSubscriber m where m.msisdn||m.serviceType like %:searchItem% ")
	List<MobileSubscriber> findMobileSubscibersMatchingSearchCriteria(String searchItem);
	
	MobileSubscriber findByMsisdn(String msisdn);
}
