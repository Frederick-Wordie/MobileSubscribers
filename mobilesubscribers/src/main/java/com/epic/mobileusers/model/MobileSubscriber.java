package com.epic.mobileusers.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MobileSubscriber implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String msisdn;
	@Column(nullable=false)
	private Integer customerIdOwner;
	@Column(nullable=false)
	private Integer customerIdUser;
	@Column(nullable=false)
	private String serviceType;
	@Column(nullable=false)
	private ZonedDateTime serviceStartDate;
}
