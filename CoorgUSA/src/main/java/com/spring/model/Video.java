package com.spring.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VIDEOID")
	private Integer videoid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USERID")
	private User user;

	@Size(min = 3, max = 100)
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	@Column(name = "TYPE", length = 100, nullable = false)
	private String type;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CONTENT", nullable = false)
	private byte[] content;

	public Integer getVideoid() {
		return videoid;
	}

	public void setVideoid(Integer videoid) {
		this.videoid = videoid;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((videoid == null) ? 0 : videoid.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Video))
			return false;
		Video other = (Video) obj;
		if (videoid == null) {
			if (other.videoid != null)
				return false;
		} else if (!videoid.equals(other.videoid))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Video [videoid=" + videoid + ", title=" + title + ", description=" + description + ", type=" + type
				+ "]";
	}
}