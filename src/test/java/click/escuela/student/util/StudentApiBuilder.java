package click.escuela.student.util;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import click.escuela.student.api.AdressApi;
import click.escuela.student.api.ParentApi;
import click.escuela.student.api.StudentApi;
import click.escuela.student.enumerator.GenderType;
import click.escuela.student.model.Adress;
import click.escuela.student.model.Course;
import click.escuela.student.model.Parent;
import click.escuela.student.model.Student;

public class StudentApiBuilder {

	private String name;
	private String surname;
	private String document;
	private GenderType gender;
	private String grade;
	private String division;
	private LocalDate birthday;
	private AdressApi adressApi;
	private String cellPhone;
	private String email;
	private ParentApi parentApi;
	private String school;
	
	public static StudentApiBuilder getBuilder() {
		return new StudentApiBuilder();
	}
	public StudentApiBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public StudentApiBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public StudentApiBuilder setDocument(String document) {
		this.document = document;
		return this;
	}
	public StudentApiBuilder setGender(GenderType gender) {
		this.gender = gender;
		return this;
	}
	public StudentApiBuilder setDivision(String division) {
		this.division = division;
		return this;
	}
	public StudentApiBuilder setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	public StudentApiBuilder setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}
	public StudentApiBuilder setAdressApi(AdressApi adressApi) {
		this.adressApi = adressApi;
		return this;
	}
	public StudentApiBuilder setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
		return this;
	}
	public StudentApiBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	public StudentApiBuilder setParentApi(ParentApi parentApi) {
		this.parentApi = parentApi;
		return this;
	}
	public StudentApiBuilder setSchool(String school) {
		this.school = school;
		return this;
	}
	
	public StudentApi getStudentApi() {
		StudentApi studentApi = new StudentApi();
		
		studentApi.setAdressApi(adressApi);
		studentApi.setBirthday(birthday);
		studentApi.setCellPhone(cellPhone);
		studentApi.setDocument(document);
		studentApi.setDivision(division);
		studentApi.setGrade(grade);
		studentApi.setEmail(email);
		studentApi.setGender(gender);
		studentApi.setName(name);
		studentApi.setName(surname);
		studentApi.setParentApi(parentApi);
		studentApi.setSchool(school);
		return new StudentApi(name, surname, document, gender, birthday, adressApi, cellPhone, email, parentApi, school, grade, division);
	}
	
	
}
