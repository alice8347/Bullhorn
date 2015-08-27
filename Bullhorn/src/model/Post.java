package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the POST database table.
 * 
 */
@Entity
@Table(name="POST", schema="TESTDB")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(schema="TESTDB", name="Post_Gen", sequenceName="Post_Seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Post_Gen")
	private long id;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="POST_DATE")
	private Date postDate;

	@Column(name="USER_ID")
	private long userId;

	public Post() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}