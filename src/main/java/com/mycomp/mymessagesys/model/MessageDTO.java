package com.mycomp.mymessagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "messages")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MessageDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "author_id")
	private Long authorId;
	private String text;
	@Column(name = "creation_date")
	private String creationDateTime;

	@Builder
	public MessageDTO(Long id, Long authorId, String text, String creationDateTime) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.text = text;
		this.creationDateTime = creationDateTime;
	}

}
