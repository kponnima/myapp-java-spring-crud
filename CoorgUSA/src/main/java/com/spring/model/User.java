package com.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID")
	private Integer userid;

	@NotEmpty
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@NotEmpty
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotEmpty
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@NotEmpty
	@Column(name = "LASTNAME", nullable = false)
	private String lastname;

	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@NotEmpty
	@Column(name = "ROLE", nullable = false)
	private String role;

	@NotEmpty
	@Column(name = "STATUS", nullable = false)
	private String status;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<News> news = new HashSet<News>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Event> events = new HashSet<Event>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Reply> reply = new HashSet<Reply>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Like> like = new HashSet<Like>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Dislike> dislike = new HashSet<Dislike>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Photo> photos = new HashSet<Photo>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Song> songs = new HashSet<Song>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Video> videos = new HashSet<Video>();

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	private String confirmpassword;

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Reply> getReply() {
		return reply;
	}

	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}

	public Set<Like> getLike() {
		return like;
	}

	public void setLike(Set<Like> like) {
		this.like = like;
	}

	public Set<Dislike> getDislike() {
		return dislike;
	}

	public void setDislike(Set<Dislike> dislike) {
		this.dislike = dislike;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstName=" + firstname + ", lastName="
				+ lastname + ", email=" + email + ", role=" + role + ", status=" + status + "]";
	}
}