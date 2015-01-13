package edu.sjsu.cmpe275.deepblue.model.serializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

public class OrganizationSerializer extends JsonSerializer<Organization> {

	@Override
	public void serialize(Organization org, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		List<Person> members = org.getMembers();

		jgen.writeStartObject();

		jgen.writeNumberField("id", org.getId());
		jgen.writeStringField("name", org.getName());
		jgen.writeStringField("description", org.getDescription());
		jgen.writeStringField("street", org.getStreet());
		jgen.writeStringField("city", org.getCity());
		jgen.writeStringField("state", org.getState());
		jgen.writeStringField("zip", org.getZip());

		jgen.writeFieldName("members");
		jgen.writeStartArray();
		for (Person member : members) {
			jgen.writeStartObject();
			jgen.writeNumberField("id", member.getId());
			jgen.writeStringField("firstname", member.getFirstname());
			jgen.writeStringField("lastname", member.getLastname());
			jgen.writeStringField("email", member.getEmail());
			jgen.writeEndObject();
		}
		jgen.writeEndArray();

		jgen.writeEndObject();
	}
}
