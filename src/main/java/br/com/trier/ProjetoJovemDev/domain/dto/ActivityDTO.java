package br.com.trier.ProjetoJovemDev.domain.dto;


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
	private String subjectName;
	private Double grade;
	private Integer activityKindId;
	private String activityKindName;


}
