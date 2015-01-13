package edu.sjsu.cmpe275.deepblue.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.sjsu.cmpe275.deepblue.model.serializer.FriendshipSerializer;

@Entity
@Table(name = "FRIENDSHIP")
@JsonSerialize(using = FriendshipSerializer.class)
public class Friendship implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Status {
		PENDING, WAITING, REJECTED, ACCEPTED
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "Friendship_Status")
	private Status status;

	private FriendshipId friendshipId = new FriendshipId();

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@EmbeddedId
	public FriendshipId getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(FriendshipId friendshipId) {
		this.friendshipId = friendshipId;
	}

	@Transient
	public Person getPerson() {
		return this.friendshipId.getPerson();
	}

	public void setPerson(Person person) {
		getFriendshipId().setPerson(person);
	}

	@Transient
	public Person getFriend() {
		return this.friendshipId.getFriend();
	}

	public void setFriend(Person friend) {
		getFriendshipId().setFriend(friend);
	}

	public Friendship() {
	}

	public Friendship(Person p1, Person p2, Status status) {
		this.friendshipId.setPerson(p1);
		this.friendshipId.setFriend(p2);
		this.status = status;
	}
	
	@Override
	public String toString() {
		String strDesc = null;
		try {
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			strDesc = ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return strDesc;
	}

	@Embeddable
	public static class FriendshipId implements Serializable {
		private static final long serialVersionUID = 1L;

		private Person person;
		private Person friend;

		@ManyToOne
		@JoinColumn(name = "Person_Id")
		public Person getPerson() {
			return person;
		}

		public void setPerson(Person person) {
			this.person = person;
		}

		@ManyToOne
		@JoinColumn(name = "Friend_Person_Id")
		public Person getFriend() {
			return friend;
		}

		public void setFriend(Person friend) {
			this.friend = friend;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((friend == null) ? 0 : friend.getEmail().hashCode());
			result = prime * result
					+ ((person == null) ? 0 : person.getEmail().hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			FriendshipId other = (FriendshipId) obj;

			if (friend == null) {
				if (other.friend != null)
					return false;
			} else if (!friend.getEmail().equals(other.friend.getEmail()))
				return false;

			if (person == null) {
				if (other.person != null)
					return false;
			} else if (!person.getEmail().equals(other.person.getEmail()))
				return false;

			return true;
		}
	}
}