package com.mycomp.mymessagesys.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
public class BaseMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@ManyToOne // https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
	@JoinColumn(name = "author_id", nullable = false) // declare a F.K field
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore // to exclude some class members from the JSON serialization
	private UserDTO author;
	@Lob
	@NotNull
	private String text;
	@Column(name = "creation_date")
	private String creationDateTime;

	@Builder
	public BaseMessage(Long id, UserDTO author, String text, String creationDateTime) {
		super();
		this.id = id;
		this.author = author;
		this.text = text;
		this.creationDateTime = creationDateTime;
	}

}
