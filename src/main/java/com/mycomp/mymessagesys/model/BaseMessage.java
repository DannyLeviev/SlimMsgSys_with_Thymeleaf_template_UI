package com.mycomp.mymessagesys.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "user_id")
	private Long authorId;
	private String text;
	@Column(name = "creation_date")
	private String creationDateTime;

	@Builder
	public BaseMessage(Long id, Long authorId, String text, String creationDateTime) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.text = text;
		this.creationDateTime = creationDateTime;
	}

}
