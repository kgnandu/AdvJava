package ttl.sf.dao;

import java.util.ArrayList;
import java.util.List;

import ttl.sf.domain.Student;

public class StudentDAO {
	
	private List<Student> students = new ArrayList<>();
	private static int nextId = 0;
	
	public StudentDAO() {

		Student student = new Student("Manoj", Student.Status.FULL_TIME);
		insert(student);

		student = new Student("Charlie", Student.Status.FULL_TIME);
		insert(student);

		student = new Student("Anjana", Student.Status.HIBERNATING);
		insert(student);

		student = new Student("Roshan", Student.Status.PART_TIME);
		insert(student);

		student = new Student("Johnny", Student.Status.PART_TIME);
		insert(student);

		student = new Student("Madhu", Student.Status.FULL_TIME);
		insert(student);

		student = new Student("Charlene", Student.Status.HIBERNATING);
		insert(student);
	}
	
	public Student insert(Student student) {
		int id = ++nextId;
		
		Student ns = new Student(id, student.getName(), student.getStatus());
		
		
		students.add(ns);
		
		return student;
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