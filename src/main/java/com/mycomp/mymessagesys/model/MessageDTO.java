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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "messages")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MessageDTO extends BaseMessage {

	// adding custom builder name to avoid compilation err  
	// "return type isn't compatible":
	@Builder(builderMethodName = "msg_builder")
	public MessageDTO(Long id, UserDTO author, String text, String creationDateTime) {
		super(id, author, text, creationDateTime);
	}

}
