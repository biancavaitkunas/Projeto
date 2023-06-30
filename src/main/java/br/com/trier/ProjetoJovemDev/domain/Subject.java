package br.com.trier.ProjetoJovemDev.domain;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
@Entity (name = "subject")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subject")
	@Setter
	private Integer id;
	
	@Column(name = "name_subject")
	private String name;
	
	/*@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	//@JoinColumn(name = "disciplina_id")
    private List<Activity> activities = new ArrayList<>();
	
	public void addActivity(Activity activity) {
		if (equal(activity)) {
			activities.add(activity);
		}
    }
	
	public boolean equal(Activity activity) {
		return this.equals(activity.getSubject());
	}*/

}
