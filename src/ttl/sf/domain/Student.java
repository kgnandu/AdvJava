package ttl.sf.domain;

public class Student {

	private int id;
	private String name;
	private Status status;

	public enum Status {
		PART_TIME, FULL_TIME, HIBERNATING
	}

	private Student(int id, String name, Status status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Student(String name, Status status) {
		this(-1, name, status);
	}

	public Student(String name, String status) {
		this(-1, name, Status.valueOf(status));
	}

	public Student() {
		this(-1, "none", Status.FULL_TIME);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setStatus(Status status) {
		this.status = status;
	}

	public static class StudentBuilder {
		private int id = -1;
		private String name;
		private Status status;
		
		public StudentBuilder name(String name) {
			this.name = name;
			
			return this;
		}

		public StudentBuilder status(Status status) {
			this.status = status;
			
			return this;
		}

		public StudentBuilder id(int id) {
			this.id = id;
			
			return this;
		}
		
		public Student build() {
			if(id == -1) {
				throw new RuntimeException("id has to be set");
			}
			return new Student(id, name, status);
		}
	}
}
