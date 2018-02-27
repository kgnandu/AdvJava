package ttl.sf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		
		Stream<Student> s1 = students.stream();
		Stream<Student> s2 = s1.filter(s -> s.getName().startsWith("M"));
		Stream<String> s3 = s2.map(s -> s.getName());
		
		List<String> r = s3.collect(Collectors.toList());
		
		Stream<String> nameWithM = students.stream()
				.peek(s -> System.out.println("Peek1 " + s))
				.filter(s -> s.getName().startsWith("M"))
				.peek(s -> System.out.println("Peek2 " + s))
				.map(s -> s.getName())
				.peek(s -> System.out.println("Peek3 " + s));
		
				//.collect(Collectors.toList());
		
		
		nameWithM.forEach(s -> System.out.println(s));

		nameWithM.forEach(s -> System.out.println(s));
		
		
	}
	
	public <T, R> List<R> getPropertyListFun(List<T> input, Function<T, R> extractor) {
		List<R> result = new ArrayList<>();
		
		for(T s : input) {
			result.add(extractor.apply(s));
		}
		
		return result;
	}

	public interface Extract<T, R> {
		public R getProperty(T s);
	}

	public <T, R> List<R> getPropertyListGen(List<T> input, Extract<T, R> extractor) {
		List<R> result = new ArrayList<>();
		
		for(T s : input) {
			result.add(extractor.getProperty(s));
		}
		
		return result;
	}
	
	
	public List<String> getPropertyList(List<Student> input, Extract<Student, String> extractor) {
		List<String> result = new ArrayList<>();
		
		for(Student s : input) {
			result.add(extractor.getProperty(s));
		}
		
		return result;
	}
	
	public class NameExtractor implements Extract<Student, String>
	{

		@Override
		public String getProperty(Student s) {
			return s.getName();
		}
		
	}

	public class IdExtractor implements Extract<Student, Integer>
	{

		@Override
		public Integer getProperty(Student s) {
			return s.getId();
		}
		
	}
	
	public List<String> getStudentNames(List<Student> input) {
		List<String> result = new ArrayList<>();
		
		for(Student s : input) {
			result.add(s.getName());
		}
		
		return result;
	}

	public List<Integer> getStudentIds(List<Student> input) {
		List<Integer> result = new ArrayList<>();
		
		for(Student s : input) {
			result.add(s.getId());
		}
		
		return result;
	}
}

