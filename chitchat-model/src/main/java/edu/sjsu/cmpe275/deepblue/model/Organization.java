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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.sjsu.cmpe275.deepblue.model.serializer.OrganizationSerializer;

/**
 * @author deepblue
 * 
 */
@Entity
@Table(name = "ORGANIZATION")
@JsonSerialize(using = OrganizationSerializer.class)
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_id")
	private long id;

	@Column(name = "org_name", nullable = false)
	private String name;

	@Column(name = "org_description")
	private String description;

	@Column(name = "org_street")
	private String street;

	@Column(name = "org_city")
	private String city;

	@Column(name = "org_state")
	private String state;

	@Column(name = "org_zip")
	private String zip;
	
//	@OneToOne
//	@JoinColumn(name="Person_Id")
//	private Person creator;
//
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name="org_member",
			joinColumns={@JoinColumn(name="org_id", referencedColumnName="org_id")},
			inverseJoinColumns={@JoinColumn(name="member_id", referencedColumnName="Person_Id", unique=true)}
			)
	private List<Person> members;

	public Organization() {
	}

	public List<Person> getMembers() {
		return members;
	}

	public void setMembers(List<Person> members) {
		this.members = members;
	}
	
	public void addMember(Person member) {
		this.members.add(member);
	}
	
	public void removeMember(Person member) {
		Iterator<Person> i = this.members.iterator();
		while (i.hasNext()) {
			Person p = i.next();
			if(p.getId() == member.getId()) {
				i.remove();
				break;
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

//	public Person getCreator() {
//		return creator;
//	}
//
//	public void setCreator(Person creator) {
//		this.creator = creator;
//	}
//
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

	private Organization(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.street = builder.street;
		this.city = builder.city;
		this.state = builder.state;
		this.zip = builder.zip;
//		this.creator = builder.creator;
		
		if (builder.id > 0) {
			this.id = builder.id;
		}
		this.members = new ArrayList<Person>();
	}

	public static class Builder {
		private final String name;

		private long id = -1;
		private String description = "";
		private String street = "";
		private String city = "";
		private String state = "";
		private String zip = "";
//		private Person creator = null;

		public Builder(String name) {
			this.name = name;
		}

		public Builder id(long id) {
			if (id > 0) {
				this.id = id;
			}
			return this;
		}

		public Builder description(String description) {
			if (description != null) {
				this.description = description;
			}
			return this;
		}

		public Builder street(String street) {
			if (street != null) {
				this.street = street;
			}
			return this;
		}

		public Builder city(String city) {
			if (city != null) {
				this.city = city;
			}
			return this;
		}

		public Builder state(String state) {
			if (state != null) {
				this.state = state;
			}
			return this;
		}

		public Builder zip(String zip) {
			if (zip != null) {
				this.zip = zip;
			}
			return this;
		}

//		public Builder creator(Person creator) {
//			if (creator != null) {
//				this.creator = creator;
//			}
//			return this;
//		}
		
		public Organization build() {
			return new Organization(this);
		}
	}
}
