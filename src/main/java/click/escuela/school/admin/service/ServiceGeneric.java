package click.escuela.school.admin.service;

import click.escuela.school.admin.exception.StudentException;

public interface ServiceGeneric <T, S>{

	public void create(T entity) throws StudentException;
	
	public S getById(String schoolId, String id, Boolean detail) throws StudentException;
	
	public void update(T entity) throws StudentException;
	
	public void delete(String id) throws StudentException;

}
