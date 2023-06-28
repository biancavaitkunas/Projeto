package br.com.trier.ProjetoJovemDev.domain.dto;


import br.com.trier.ProjetoJovemDev.domain.ActivityKind;
import br.com.trier.ProjetoJovemDev.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
	
	private Integer id;
	private String description;
	private String deliveryDate;
	private Integer subjectId;
	private Double grade;
	private Integer activityKindId;

}
