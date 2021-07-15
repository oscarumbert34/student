package click.escuela.school.admin.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import click.escuela.school.admin.enumerator.DocumentType;
import click.escuela.school.admin.model.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO {

	public TeacherDTO(String id, Teacher teacher, AdressDTO adress) {
		super();
		this.id = id;
		this.name = teacher.getName();
		this.surname = teacher.getSurname();
		this.documentType = teacher.getDocumentType();
		this.document = teacher.getDocument();
		this.birthday = teacher.getBirthday();
		this.adress = adress;
		this.cellPhone = teacher.getCellPhone();
		this.email = teacher.getEmail();
		this.schoolId = teacher.getSchoolId();
	}

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "surname")
	private String surname;

	@JsonProperty(value = "documentType")
	private DocumentType documentType;

	@JsonProperty(value = "document")
	private String document;

	@JsonProperty(value = "birthday")
	private LocalDate birthday;

	@JsonProperty(value = "adress")
	private AdressDTO adress;

	@JsonProperty(value = "cellPhone")
	private String cellPhone;

	@JsonProperty(value = "email")
	private String email;
	
	@Column(name = "school_id", nullable = false)
	private Integer schoolId;
}