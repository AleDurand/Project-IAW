package proyect.model;

public class Teacher {
	private long id;
	private String lastname, name;
	
	public Teacher() {
		
	}

	public Teacher(long id, String lastname, String name) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
