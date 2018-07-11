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
@Table(name = "songs")
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SONGID")
	private Integer songid;

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

	public Integer getSongid() {
		return songid;
	}

	public void setSongid(Integer songid) {
		this.songid = songid;
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
		result = prime * result + ((songid == null) ? 0 : songid.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Song))
			return false;
		Song other = (Song) obj;
		if (songid == null) {
			if (other.songid != null)
				return false;
		} else if (!songid.equals(other.songid))
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
		return "Song [songid=" + songid + ", title=" + title + ", description=" + description + ", type=" + type + "]";
	}
}