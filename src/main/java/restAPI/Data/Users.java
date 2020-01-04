package restAPI.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class Users {

	String name;
	String job;
	// Users usersResObj = mapper.readValue(resposeString, Users.class); we have to
	// define all the fetched attributes after creation

	String id;
	String createdAt;

	public Users() {
		// Since only parameterized constructor is used, we have to provide default,
		// otherwise Exception will occur during Runtime
	}

	public Users(String name, String job) {

		this.name = name;
		this.job = job;
	}

	// getters and setters methods:
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}