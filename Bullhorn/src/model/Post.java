package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the POST database table.
 * 
 */
@Entity
@Table(name="Post", schema="TESTDB")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="POST_ID_GENERATOR", sequenceName="POST_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_ID_GENERATOR")
	private long id;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="POST_DATE")
	private Date postDate;

	//bi-directional many-to-one association to Tweetuser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Tweetuser tweetuser;

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

	public Tweetuser getTweetuser() {
		return this.tweetuser;
	}

	public void setTweetuser(Tweetuser tweetuser) {
		this.tweetuser = tweetuser;
	}

}