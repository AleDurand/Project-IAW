package proyect.model;

import java.util.Date;

public class Review {
	private long id;
	private String name, description;
	private Date date;
	private int published;
	
	public Review() {
		
	}

	public Review(long id, String name, String description, Date date, int published) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPublished() {
		return published;
	}

	public void setPublished(int published) {
		this.published = published;
	}
	
	
	
	

}
