package ttl.sf.app;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ttl.sf.domain.Student;
import ttl.sf.service.StudentService;

public class RegAppSorter {

	public static void main(String[] args) {
		RegAppSorter ra = new RegAppSorter();
		ra.go();
	}

	public void go() {
		StudentService studentService = new StudentService();

		List<Student> students = studentService.getAllStudents();

		// Collections.sort(students);

		NameComparator nc = new NameComparator();

		// Collections.sort(students, nc);
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student first, Student second) {
				return first.getName().compareTo(second.getName());
			}
		});
		
		Comparator<Student> cs = Comparator.comparing(Student::getName);
		cs = cs.thenComparing(Student::getId);

		Collections.sort(students, (first, second) -> {
			return first.getName().compareTo(second.getName());	
		});

		Collections.sort(students, (first, second) -> first.getStatus().compareTo(second.getStatus()));

		students.forEach(s -> System.out.println(s));
		
		sort("hello", (s) -> s.length());
	}

	
	public void sort(String input, MyInterface mi) {
		int result = mi.doStuff(input);
		System.out.println(result);
	}

	
	@FunctionalInterface
	public interface MyInterface
	{
		public int doStuff(String s);
	}

	public class NameComparator implements Comparator<Student> {

		@Override
		public int compare(Student first, Student second) {
			return first.getName().compareTo(second.getName());
		}

	}
}
