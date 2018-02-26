package ttl.sf.domain;

public class Student implements Comparable<Student>{

	private int id;
	private String name;
	private Status status;

	public enum Status {
		PART_TIME("PartTime", 10), 
		FULL_TIME("FullTime", 25),
		HIBERNATING("Hibernating", 34);
	
		private String prettyName;
		private int dbCode;

		Status(String prettyName, int dbCode) {
			this.prettyName = prettyName;
			this.dbCode = dbCode;
		}
		
		public String toString() {
			return prettyName;
		}
		
		public static Status fromPretty(String pretty) {
			for(Status status : values()) {
				if(status.prettyName.equals(pretty)) {
					return status;
				}
			}
			
			throw new IllegalArgumentException("No Status found for " + pretty);
		}
		
		public int getDbCode() {
			return dbCode;
		}
			
	}

	private Student(int id, String name, Status status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Student(String name, Status status) {
		this(-1, name, status);
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

	@Override
	public int compareTo(Student other) {
		return this.id - other.id;
	}
}
