package com.mycomp.mymessagesys.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "messages")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MessageDTO extends BaseMessage {

	// adding custom builder name to avoid compilation err 'return 
	// type isn't compatible':
	@Builder(builderMethodName = "msg_builder")
	public MessageDTO(Long id, Long authorId, String text, String creationDateTime) {
		super(id, authorId, text, creationDateTime);
	}

}
