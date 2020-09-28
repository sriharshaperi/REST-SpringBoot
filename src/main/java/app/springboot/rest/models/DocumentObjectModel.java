package app.springboot.rest.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "document")
public class DocumentObjectModel {
	
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private List<ContentModel> contentModel;
	
	
	public String getId() {
		return id;
	}
	public List<ContentModel> getContentModel() {
		return contentModel;
	}
	public void setContentModel(List<ContentModel> contentModel) {
		this.contentModel = contentModel;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
