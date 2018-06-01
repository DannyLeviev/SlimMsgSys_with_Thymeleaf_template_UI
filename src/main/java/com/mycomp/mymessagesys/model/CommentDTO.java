package com.mycomp.mymessagesys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "comments")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CommentDTO extends BaseMessage {

	@Column(name = "parent_id")
	private Long parentMsgId;

	@Builder(builderMethodName = "cmnt_builder") // adding custom builder name to avoid compilation issue 'return type is
												// not compatible...'
	public CommentDTO(Long id, Long authorId, String text, String creationDateTime, Long parentMsgId) {
		super(id, authorId, text, creationDateTime);
		this.parentMsgId = parentMsgId;
	}

}
