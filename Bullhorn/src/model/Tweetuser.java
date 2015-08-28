package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TWEETUSER database table.
 * 
 */
@Entity
@Table(name="Tweetuser", schema="TESTDB")
@NamedQuery(name="Tweetuser.findAll", query="SELECT t FROM Tweetuser t")
public class Tweetuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(schema="TESTDB", name="TWEETUSER_ID_GENERATOR", sequenceName="TWEETUSER_ID_GENERATOR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TWEETUSER_ID_GENERATOR")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="JOIN_DATE")
	private Date joinDate;

	private String motto;

	private String name;

	private String password;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="tweetuser", cascade = CascadeType.ALL)
	private List<Post> posts;

	public Tweetuser() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getMotto() {
		return this.motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setTweetuser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setTweetuser(null);

		return post;
	}

}