package br.com.trier.ProjetoJovemDev.domain;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
@Entity (name = "activity")
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_activity")
	@Setter
	private Integer id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "deliveryDate")
	private ZonedDateTime deliveryDate;
	
	@ManyToOne
	private Subject subject;
	
	@Column(name = "grade")
	private Double grade;
	
	@ManyToOne
	private ActivityKind activityKind;

}
