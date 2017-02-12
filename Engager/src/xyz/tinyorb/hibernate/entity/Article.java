package xyz.tinyorb.hibernate.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Article")
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idArticle")
	private int idArticle;
	
	@Column(name="Author")
	private String Author;
	
	@Column(name="Category")
	private String Category;
	
	@Column(name="saveData")
	private String sData;
	
	@Column(name="LastModified")
	private Date PDate;
	
	
	@Column(name="Heading")
	private String Heading;
	
	@Column(name="PubId")
	private Integer PID;

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getsData() {
		return sData;
	}

	public void setsData(String sData) {
		this.sData = sData;
	}

	public Date getPDate() {
		return PDate;
	}

	public void setPDate(Date pDate) {
		PDate = pDate;
	}

	public String getHeading() {
		return Heading;
	}

	public void setHeading(String heading) {
		Heading = heading;
	}

	public int getIdArticle() {
		return idArticle;
	}
	
	
	

}
