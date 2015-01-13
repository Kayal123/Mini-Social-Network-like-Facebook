/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//import edu.sjsu.cmpe275.deepblue.model.serializer.PostSerializer;

/**
 * @author deepblue
 *
 */
@Entity
@Table(name = "POST")
//@JsonSerialize(using = PostSerializer.class)
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Person author;

	@Column(name = "content", columnDefinition = "text", nullable = false)
	private String content;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@Column
	private Boolean isPrivate;
	
	@PreUpdate
	@PrePersist
	void updateDates() {
	  if (dateCreated == null) {
	    dateCreated = new Date();
	  }
	  lastModified = new Date();
	  if(isPrivate == null) setIsPrivate("private");
	}

	@Transient
	private String privacy;
	
	public Post() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Boolean isPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String privacy) {
		Boolean isprivate=null;
		if (getPrivacy().equals("public")) isprivate = new Boolean(false);
		else isprivate = new Boolean(true);
		this.isPrivate = isprivate;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

}
