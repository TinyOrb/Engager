package xyz.tinyorb.hibernate.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Publish_Post")
public class Publish_Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPublish_Post")
	private int idPublish_Post;
	
	@Column(name="Rating")
	private float Rating;
	
	@Column(name="Tags")
	private String Tags;
	
	@Column(name="ViewCount")
	private int ViewCount;
	
	@Column(name="RateCount")
	private int RateCount;
	
	@Column(name="PAuthor")
	private String PAuthor;
	
	@Column(name="Date")
	private Date date;
	
	@Column(name="Heading")
	private String Heading;
	
	@Column(name="Category")
	private String Category;

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public int getViewCount() {
		return ViewCount;
	}

	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
	}

	public int getRateCount() {
		return RateCount;
	}

	public void setRateCount(int rateCount) {
		RateCount = rateCount;
	}

	public String getPAuthor() {
		return PAuthor;
	}

	public void setPAuthor(String pAuthor) {
		PAuthor = pAuthor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHeading() {
		return Heading;
	}

	public void setHeading(String heading) {
		Heading = heading;
	}

	

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public int getIdPublish_Post() {
		return idPublish_Post;
	}
	
	
	
}
