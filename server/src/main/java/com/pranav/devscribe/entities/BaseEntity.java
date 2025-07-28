package com.pranav.devscribe.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

	@Id
	@Column(name= "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private long id;
	
	@CreationTimestamp
	@Column(name = "Creation_Date")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate creationDate;
	
	@UpdateTimestamp
	@Column(name = "Updated_On")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedOn;
}
