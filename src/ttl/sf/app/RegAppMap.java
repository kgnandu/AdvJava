package ttl.sf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ttl.sf.domain.Student;
import ttl.sf.service.StudentService;

public class RegAppMap {

	public static void main(String[] args) {
		RegAppMap ra = new RegAppMap();
		ra.go();
	}

	public void go() {
		StudentService studentService = new StudentService();

		List<Student> students = studentService.getAllStudents();
		
		List<String> result = getStudentNames(students);
		
		Extract<Student, String> nameExtractor = new NameExtractor();
		//List<String> result2 = getPropertyListGen(students, nameExtractor);
		List<String> result2 = getPropertyListFun(students, (s) -> s.getName());
		
		Extract<Student, Integer> idExtractor = new IdExtractor();

		//List<Integer> ids = getPropertyListGen(students, idExtractor);
		List<Integer> ids = getPropertyListFun(students, (s) -> s.getId());

		ids.forEach(s -> System.out.println(s));
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

