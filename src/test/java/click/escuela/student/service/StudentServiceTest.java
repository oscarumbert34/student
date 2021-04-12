package click.escuela.student.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.student.api.AdressApi;
import click.escuela.student.api.ParentApi;
import click.escuela.student.api.StudentApi;
import click.escuela.student.enumerator.GenderType;
import click.escuela.student.exception.TransactionException;
import click.escuela.student.mapper.Mapper;
import click.escuela.student.model.Adress;
import click.escuela.student.model.Parent;
import click.escuela.student.model.Student;
import click.escuela.student.repository.StudentRepository;
import click.escuela.student.service.impl.StudentServiceImpl;
import click.escuela.student.util.StudentApiBuilder;
import click.escuela.student.util.StudentBuilder;

@PrepareForTest({Mapper.class})
public class StudentServiceTest{
	
	@Mock
	private StudentRepository studentRepository;
	
	private StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
	private StudentApi studentApi;
	
	@Before
	public void setUp() {
		Student student = StudentBuilder.getBuilder().setAbsences(3).setBirthday(LocalDate.now()).setCellPhone("535435").
				setDocument("342343232").setDivision("B").setGrade("2°").setEmail("oscar@gmail.com").setGender(GenderType.MALE).setName("oscar").setParent(new Parent()).getStudent();
		
		ParentApi parentApi = new ParentApi();
		parentApi.setAdressApi(new AdressApi());
		
		studentApi = StudentApiBuilder.getBuilder().setAdressApi(new AdressApi()).setBirthday(LocalDate.now())
				.setCellPhone("4534543").setDivision("C").setGrade("3°").setDocument("435345").setEmail("oscar@gmail.com").setGender(GenderType.MALE)
				.setName("oscar").setParentApi(parentApi).setSchool("1234").getStudentApi();
		Optional<Student> optional = Optional.of(student);

		Mockito.when(Mapper.mapperToStudent(studentApi)).thenReturn(student);
		Mockito.when(Mapper.mapperToAdress(Mockito.any())).thenReturn(new Adress());

		Mockito.when(Mapper.mapperToParent(Mockito.any())).thenReturn(new Parent());

		Mockito.when(studentRepository.save(Mockito.any())).thenReturn(student);
		//Mockito.when(clientRepository.findById(1L)).thenReturn(optional);
		//Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(new Client(1L,"23423432","oscar",LocalDateTime.of(2020, 10, 20, 12, 12))));

		//Mockito.doNothing().when(clientRepository).delete(Mockito.any());
		
		//inyecta en el servicio el objeto repository
		ReflectionTestUtils.setField(studentServiceImpl, "studentRepository", studentRepository);
	}
	
	@Test(expected = Test.None.class)
	public void whenCreateIsOk() throws TransactionException {

		studentServiceImpl.create(studentApi);
	}
	@Test(expected = Test.None.class)
	public void whenCreateIsError() throws TransactionException {
		StudentApi studentApi = StudentApiBuilder.getBuilder().setAdressApi(new AdressApi()).setBirthday(LocalDate.now())
				.setCellPhone("4534543").setDocument("55555").setDivision("F").setGrade("3°").setEmail("oscar@gmail.com").setGender(GenderType.MALE)
				.setName("oscar").setParentApi(new ParentApi()).setSchool("1234").getStudentApi();
		
		assertThatExceptionOfType(TransactionException.class)
		  .isThrownBy(() -> {
				studentServiceImpl.create(studentApi);
		}).withMessage("El Cliente que quiere eliminar no existe");

	}
	
}