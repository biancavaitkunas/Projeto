package br.com.trier.ProjetoJovemDev.domain;

import java.time.ZonedDateTime;

import br.com.trier.ProjetoJovemDev.domain.dto.UserDTO;
import br.com.trier.ProjetoJovemDev.utils.DateUtils;
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
@Entity (name = "usuario")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	@Setter
	private Integer id;
	
	@Column(name = "name_user")
	private String name;
	
	@Column(name = "email_user", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "dateOfBirth")
	private ZonedDateTime dateOfBirth;
	
	public User (UserDTO dto) {
		this(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getGender(), DateUtils.strToZonedDateTime(dto.getDateOfBirth()));
	}
	
	public UserDTO ToDto() {
		return new UserDTO(this.id, this.name, this.email, this.password, this.gender, DateUtils.ZonedDateTimeToStr(dateOfBirth));
	}
	

}
