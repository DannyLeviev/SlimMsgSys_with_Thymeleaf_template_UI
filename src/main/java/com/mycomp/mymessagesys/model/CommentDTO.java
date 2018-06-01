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

	// adding custom builder name to avoid compilation err "return type isn't compatible": 
	@Builder(builderMethodName = "cmnt_builder")
	public CommentDTO(Long id, UserDTO author, String text, String creationDateTime, Long parentMsgId) {
		super(id, author, text, creationDateTime);
		this.parentMsgId = parentMsgId;
	}

}
