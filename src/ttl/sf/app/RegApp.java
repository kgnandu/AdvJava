package ttl.sf.app;

import java.util.List;

import ttl.sf.domain.Student;
import ttl.sf.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		StudentService studentService = new StudentService();
		
		List<Student> students = studentService.getAllStudents();
		
		students.forEach(s -> System.out.println(s));
		
		Student one = studentService.getStudent(1);
		System.out.println("one is " + one);
		
		String name = "Joe";
		Student.Status status = Student.Status.FULL_TIME;
		Student student = new Student(name, status);

		studentService.addStudent(student);
		
		System.out.println(studentService.getAllStudents());
	}
}
