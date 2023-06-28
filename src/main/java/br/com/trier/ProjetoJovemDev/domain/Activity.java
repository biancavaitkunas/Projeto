package br.com.trier.ProjetoJovemDev.domain;

import java.time.ZonedDateTime;

import br.com.trier.ProjetoJovemDev.domain.dto.ActivityDTO;
import br.com.trier.ProjetoJovemDev.domain.dto.UserDTO;
import br.com.trier.ProjetoJovemDev.utils.DateUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
	
	/*public Activity (ActivityDTO dto) {
		this(dto.getId(), dto.getDescription(), DateUtils.strToZonedDateTime(dto.getDeliveryDate()), dto.getSubjectId(), dto.getGrade(), dto.getActivityKindId());
	}*/
	
	public Activity (ActivityDTO dto, Subject sub, ActivityKind ak) {
		this(dto.getId(), dto.getDescription(), DateUtils.strToZonedDateTime(dto.getDeliveryDate()), sub, dto.getGrade(), ak);
	}
	
	public ActivityDTO ToDto() {
		return new ActivityDTO(this.id, this.description, DateUtils.ZonedDateTimeToStr(deliveryDate), this.subject.getId(), this.grade, this.activityKind.getId());
	}


}
