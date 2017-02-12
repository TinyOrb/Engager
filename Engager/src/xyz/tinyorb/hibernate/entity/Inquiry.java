package xyz.tinyorb.hibernate.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Inquiry")
public class Inquiry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idInquiry")
	private int id;
	
	@Column(name="user")
	private String user;
	
	@Column(name="type")
	private String type;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="opendate")
	private Date opendate;
	
	@Column(name="closedate")
	private Date closeddate;
	
	@Column(name="openstatus")
	private Boolean openstatus;
	
	@Column(name="reopendate")
	private Date reopendate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	public Date getCloseddate() {
		return closeddate;
	}

	public void setCloseddate(Date closeddate) {
		this.closeddate = closeddate;
	}

	

	public Boolean getOpenstatus() {
		return openstatus;
	}

	public void setOpenstatus(Boolean openstatus) {
		this.openstatus = openstatus;
	}

	public Date getReopendate() {
		return reopendate;
	}

	public void setReopendate(Date reopendate) {
		this.reopendate = reopendate;
	}
	
	
	
}
