package ttl.sf.app;

import java.util.List;

import ttl.sf.domain.Student;
import ttl.sf.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		StudentService studentService = new StudentService();
		
		List<Student> students = studentService.getAllStudents();
		
		students.forEach(s -> System.out.println(s));
	}
}
