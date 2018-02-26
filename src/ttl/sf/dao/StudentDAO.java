package ttl.sf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ttl.sf.domain.Student;
import ttl.sf.domain.Student.StudentBuilder;

public class StudentDAO {
	
	private List<Student> students = new ArrayList<>();
	//private static int nextId = 0;
	private static AtomicInteger nextId = new AtomicInteger(0);
	
	public StudentDAO() {

		Student student = new Student("Manoj", Student.Status.FULL_TIME);
		insert(student);

		student = new Student("Charlie", Student.Status.FULL_TIME);
		insert(student);

		student = new Student("Anjana", Student.Status.HIBERNATING);
		insert(student);

		student = new Student("Roshan", Student.Status.PART_TIME);
		insert(student);
	}
	
	public Student insert(Student student) {
		//int newId = nextId++;
		int newId = nextId.incrementAndGet();
		
		//Student newStudent = new Student(newId, student.getName(), student.getStatus());
		Student newStudent = new StudentBuilder().name(student.getName())
				.status(student.getStatus()).build();
		
		students.add(newStudent);
		
		return newStudent;
	}
	
	public List<Student> selectAll() {
		return students;

	}
	
	
	public Student select(int id) {
		for(Student s : students) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
}