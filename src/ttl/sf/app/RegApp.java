package ttl.sf.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ttl.sf.domain.Student;
import ttl.sf.service.StudentService;

public class RegApp {

	public static void main(String[] args) {
		RegApp ra = new RegApp();
		//ra.goReduce(10);
		
		Supplier<Integer> s = ra.supplierFactory(35);
		Supplier<Integer> s2 = ra.supplierFactory(100);
		
		System.out.println(s.get());
		System.out.println(s2.get());
	}
	
	public Supplier<Integer> supplierFactory(int val) {
		Supplier<Integer> supplier = () -> {
			return val;
		};

		return supplier;
	}

	public void goReduce(int val) {
		StudentService studentService = new StudentService();

		List<Student> students = studentService.getAllStudents();
		
		long l = students.stream()
				.filter(s -> s.getName().startsWith("M"))
				.map(s -> s.getName().length())
				//.reduce(0, (accum, next) -> accum + next);
				.reduce(val, (accum, next) -> {
					return accum + next;
				});

		System.out.println("result = " + l);
		
	}
	
	public void go2() {
		StudentService studentService = new StudentService();

		List<Student> students = studentService.getAllStudents();
		
		Stream<Student> s1 = students.stream();
		Stream<Student> s2 = s1.filter(s -> s.getName().startsWith("M"));
		Stream<String> s3 = s2.map(s -> s.getName());
		
		List<String> r = s3.collect(Collectors.toList());
		
		Supplier<List<Student>> listSupplier = () -> { return new ArrayList<>();};
		//Supplier<List<Student>> listSupplier = ArrayList::new;
		
		BiConsumer<List<Student>, Student> accumulator = (list, currStudent) -> list.add(currStudent);
		
		BiConsumer<List<Student>, List<Student>> combiner = (list1, list2) -> list1.addAll(list2);
		
		List<Student> result = students.stream()
				//.filter(s -> s.getName().startsWith("M"))
				.filter(this::byFirstName)
				//.collect(listSupplier, accumulator, combiner);
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		Map<Character, Long> mapResult = students.stream()
				.collect(Collectors.groupingBy((s) -> s.getName().charAt(0), Collectors.counting())
			);

		
		
		Map<Integer, Student> mapStudents = students.stream()
				.collect(Collectors.toMap(Student::getId, Function.identity()));
				//.collect(Collectors.toMap(s -> s.getId(), s -> s));
		
		
		
		/*
		Map<Integer, Student> mapResult = students.stream()
				.collect(Collectors.groupingBy((s) -> s.getName().charAt(0), Collectors.m
			);
			*/
		
		
		
		mapStudents.forEach((k, v) -> System.out.printf("k = %d, v = %s%n", k, v));

	}
	
	public boolean  byFirstName(Student s) {
		
		return true;
	}

	public void myPrettyPrint(Student s) {
		System.out.println("Whoo hoo" + s);
	}
	
}

