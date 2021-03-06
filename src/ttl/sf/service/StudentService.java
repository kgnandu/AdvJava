package ttl.sf.service;

import java.util.List;

import ttl.sf.dao.StudentDAO;
import ttl.sf.domain.Student;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();
	
	public Student getStudent(int id) {
		return dao.select(id);
	}
	
	public List<Student> getAllStudents() {
		return dao.selectAll();
	}
	
	public Student addStudent(Student student) {

		student = dao.insert(student);
		
		return student;
	}
}
