package com.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "replies")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPLYID")
	private Integer replyid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USERID")
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "POSTID")
	private Post post;

	@Size(min = 3)
	@Column(name = "BODY", nullable = false)
	private String body;

	@Column(name = "DATE")
	private Date date;

	public Reply() {
	}

	public Reply(User user, Post post, String body) {
		this.user = user;
		this.post = post;
		this.body = body;
	}

	public Integer getReplyid() {
		return replyid;
	}

	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((replyid == null) ? 0 : replyid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reply))
			return false;
		Reply other = (Reply) obj;
		if (replyid == null) {
			if (other.replyid != null)
				return false;
		} else if (!replyid.equals(other.replyid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reply [replyid=" + replyid + ", user=" + user + ", post=" + post + ", body=" + body + ", date=" + date
				+ "]";
	}

}
