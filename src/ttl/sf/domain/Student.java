package ttl.sf.domain;

public class Student {

	private int id;
	private String name;
	private Status status;
	
	public enum Status {
		PART_TIME(2),
		FULL_TIME(20),
		HIBERNATING(-144);
		
		private int code;
		Status(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}

	public Student(int id, String name, Status status) {
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
}
