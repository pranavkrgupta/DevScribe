package com.pranav.devscribe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Blog")
@NoArgsConstructor
@Getter
@Setter
public class Blog extends BaseEntity{
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
