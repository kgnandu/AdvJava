package ttl.sf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
		
		//List<Student> result = firstNamesStartingWith(students, "M");
		
		StartsWithChecker swc = new StartsWithChecker("M");
		

		List<Student> result = filter(students, new Predicate<Student>() {
			public boolean test(Student s) {
				return s.getName().startsWith("M");
			}
		});

		List<Student> result3 = filter(students, s -> s.getName().startsWith("M"));
		
		result3.forEach(s -> System.out.println(s));


	}
	
	public <T> List<T> filter(List<T> input, Predicate<T> checker) {
		List<T> result = new ArrayList<>();
		for(T s : input) {
			if(checker.test(s)) {
				result.add(s);
			}
		}
		
		return result;
	}

	public interface Checker<T> {
		public boolean check(T s);
	}
	
	public class StringSSChecker implements Checker<String>
	{

		@Override
		public boolean check(String s) {
			return s.startsWith("o");
		}
		
	}

	public class StartsWithChecker implements Checker<Student> 
	{
		private String prefix;
		public StartsWithChecker(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public boolean check(Student s) {
			return s.getName().startsWith(prefix);
		}
	}

	public class LengthChecker implements Checker<Student> 
	{
		private int length;

		public LengthChecker(int prefix) {
			this.length = prefix;
		}

		@Override
		public boolean check(Student s) {
			return s.getName().length() > length;
		}
	}


	public List<Student> firstNamesStartingWith(List<Student> students, String start) {
		List<Student> result = new ArrayList<>();
		for(Student s : students) {
			if(s.getName().startsWith(start)) {
				result.add(s);
			}
		}
		
		return result;
	}

	public List<Student> lengthLessThan(List<Student> students, int length) {
		List<Student> result = new ArrayList<>();
		for(Student s : students) {
			if(s.getName().length() < length) {
				result.add(s);
			}
		}
		
		return result;
	}
}
