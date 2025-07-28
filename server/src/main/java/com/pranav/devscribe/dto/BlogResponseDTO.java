package com.pranav.devscribe.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogResponseDTO {
	private Long id;
	private String title;
	private String contents;
	private String userFullName;
	private String categoryTitle;
	private LocalDate creationDate;
	private LocalDateTime updatedOn;
}
