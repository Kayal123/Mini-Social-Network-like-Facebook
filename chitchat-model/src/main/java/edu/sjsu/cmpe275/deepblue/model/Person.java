/**
 * 
 */
package edu.sjsu.cmpe275.deepblue.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.sjsu.cmpe275.deepblue.model.serializer.PersonSerializer;

/**
 * @author deepblue
 * 
 */
@Entity
@Table(name = "PERSON")
@JsonSerialize(using = PersonSerializer.class)
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Person_Id")
	private long id;

	@Column(name = "First_Name", nullable = false)
	private String firstname;

	@Column(name = "Last_Name", nullable = false)
	private String lastname;

	@Column(name = "Email", nullable = false, unique = true)
	private String email;

	@Column(name = "Person_Desc")
	private String description;

	@Column(name = "Person_Street")
	private String street;

	@Column(name = "Person_City")
	private String city;

	@Column(name = "Person_State")
	private String state;

	@Column(name = "Person_Zip")
	private String zip;

	@Transient
	@JsonIgnore
	private long orgId;

//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="org_id")
//	private Organization org;
//
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "friendshipId.person")
	private List<Friendship> friendships = new ArrayList<Friendship>();

	public List<Friendship> getFriendships() {
		return friendships;
	}

	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
	}

	public Person() {
	}

	public String getCity() {
		return city;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

//	public Organization getOrg() {
//		return org;
//	}
//
	public long getOrgId() {
		return orgId;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

//	public void setOrg(Organization org) {
//		this.org = org;
//	}
//
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZip(String zip) {
		this.zip = zip;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		return true;
	}

	private Person(Builder builder) {
		this.firstname = builder.firstname;
		this.lastname = builder.lastname;
		this.email = builder.email;
		this.description = builder.description;
		this.street = builder.street;
		this.city = builder.city;
		this.state = builder.state;
		this.zip = builder.zip;

		if (builder.id > 0) {
			this.id = builder.id;
		}

		this.orgId = builder.orgId;
		this.friendships = new ArrayList<Friendship>();
	}

	public static class Builder {
		private final String firstname;
		private final String lastname;
		private final String email;

		private long id = 0;
		private String description = "";
		private String street = "";
		private String city = "";
		private String state = "";
		private String zip = "";
		private long orgId = 0;

		public Builder(String firstname, String lastname, String email) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
		}

		public Person build() {
			return new Person(this);
		}

		public Builder city(String city) {
			if (city != null) {
				this.city = city;
			}
			return this;
		}

		public Builder description(String description) {
			if (description != null) {
				this.description = description;
			}
			return this;
		}

		public Builder id(long id) {
			if (id > 0) {
				this.id = id;
			}
			return this;
		}

		public Builder organization(String orgId) {
			if (orgId != null && !orgId.equalsIgnoreCase("") && Long.parseLong(orgId) > 0) {
				this.orgId = Long.parseLong(orgId);
			}
			return this;
		}

		public Builder state(String state) {
			if (state != null) {
				this.state = state;
			}
			return this;
		}

		public Builder street(String street) {
			if (street != null) {
				this.street = street;
			}
			return this;
		}

		public Builder zip(String zip) {
			if (zip != null) {
				this.zip = zip;
			}
			return this;
		}
	}
}
