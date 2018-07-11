package com.spring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POSTID")
	private Integer postid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USERID")
	private User user;

	@Size(min = 3, max = 100)
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Size(min = 3)
	@Column(name = "BODY", nullable = false)
	private String body;

	@Column(name = "DATE")
	private Date date;

	@Column(name = "LIKES_COUNT")
	private Integer likes;

	@Column(name = "DISLIKES_COUNT")
	private Integer dislikes;

	@Column(name = "REPLIES_COUNT")
	private Integer replies;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Reply> reply = new HashSet<Reply>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Like> like = new HashSet<Like>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Dislike> dislike = new HashSet<Dislike>();

	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public Integer getReplies() {
		return replies;
	}

	public void setReplies(Integer replies) {
		this.replies = replies;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postid == null) ? 0 : postid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Post))
			return false;
		Post other = (Post) obj;
		if (postid == null) {
			if (other.postid != null)
				return false;
		} else if (!postid.equals(other.postid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [postid=" + postid + ", user=" + user + ", title=" + title + ", body=" + body + "]";
	}

}
