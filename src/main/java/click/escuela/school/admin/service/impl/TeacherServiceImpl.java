package click.escuela.school.admin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.school.admin.api.TeacherApi;
import click.escuela.school.admin.dto.TeacherDTO;
import click.escuela.school.admin.enumerator.TeacherMessage;
import click.escuela.school.admin.exception.TeacherException;
import click.escuela.school.admin.mapper.Mapper;
import click.escuela.school.admin.model.Teacher;
import click.escuela.school.admin.repository.TeacherRepository;

@Service
public class TeacherServiceImpl {

	@Autowired
	private TeacherRepository teacherRepository;

	public void create(TeacherApi teacherApi) throws TeacherException {
		try {
			Teacher teacher = Mapper.mapperToTeacher(teacherApi);
			teacherRepository.save(teacher);
		} catch (Exception e) {
			throw new TeacherException(TeacherMessage.CREATE_ERROR);
		}
	}

	public void update(TeacherApi teacherApi) throws TeacherException {
		findById(teacherApi.getId())
				.ifPresent(teacher -> teacherRepository.save(Mapper.mapperToTeacher(teacherApi, teacher)));
	}

	public List<TeacherDTO> findAll() {
		List<Teacher> teachers = teacherRepository.findAll();
		return Mapper.mapperToTeachersDTO(teachers);
	}

	public Optional<Teacher> findById(String idTeacher) throws TeacherException {
		return Optional.of(teacherRepository.findById(UUID.fromString(idTeacher))
				.orElseThrow(() -> new TeacherException(TeacherMessage.GET_ERROR)));
	}

	public TeacherDTO getById(String id) throws TeacherException {
		Teacher teacher = findById(id).orElseThrow(() -> new TeacherException(TeacherMessage.GET_ERROR));
		return Mapper.mapperToTeacherDTO(teacher);
	}

	public List<TeacherDTO> getBySchoolId(String schoolId) {
		return Mapper.mapperToTeachersDTO(teacherRepository.findBySchoolId(Integer.valueOf(schoolId)));
	}

	public List<TeacherDTO> getByCourseId(String courseId) {
		return Mapper.mapperToTeachersDTO(teacherRepository.findByCourseId(UUID.fromString(courseId)));
	}

	public void exists(TeacherApi teacherApi) throws TeacherException {
		Optional<Teacher> teacherExist = teacherRepository.findByDocumentAndGender(teacherApi.getDocument(),
				Mapper.mapperToEnum(teacherApi.getGender()));
		if (teacherExist.isPresent()) {
			throw new TeacherException(TeacherMessage.EXIST);
		}
	}

	public Teacher addCourseId(String idTeacher, String idCourse) throws TeacherException {
		Teacher teacher = findById(idTeacher)
				.orElseThrow(() -> new TeacherException(TeacherMessage.GET_ERROR));
		teacher.setCourseId(UUID.fromString(idCourse));
		teacherRepository.save(teacher);
		return teacher;
	}

	public void deleteCourseId(String teacherId) throws TeacherException {
		Teacher teacher = findById(teacherId)
				.orElseThrow(() -> new TeacherException(TeacherMessage.GET_ERROR));
		teacher.setCourseId(null);
		teacherRepository.save(teacher);
	}

}
