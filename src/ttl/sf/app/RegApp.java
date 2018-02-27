package ttl.sf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ttl.sf.domain.Student;
import ttl.sf.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		RegApp ra = new RegApp();
		ra.go();
	}

	public void go() {
		StudentService studentService = new StudentService();

		List<Student> students = studentService.getAllStudents();
		

	}
}
