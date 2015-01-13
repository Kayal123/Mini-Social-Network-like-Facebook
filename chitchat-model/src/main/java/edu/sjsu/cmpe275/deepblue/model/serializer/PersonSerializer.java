package edu.sjsu.cmpe275.deepblue.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Organization;
import edu.sjsu.cmpe275.deepblue.model.Person;

public class PersonSerializer extends JsonSerializer<Person> {

	@Override
	public void serialize(Person p, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
//		Organization org = p.getOrg();

		jgen.writeStartObject();

		jgen.writeNumberField("id", p.getId());
		jgen.writeStringField("firstname", p.getFirstname());
		jgen.writeStringField("lastname", p.getLastname());
		jgen.writeStringField("email", p.getEmail());
		jgen.writeStringField("description", p.getDescription());
		jgen.writeStringField("street", p.getStreet());
		jgen.writeStringField("city", p.getCity());
		jgen.writeStringField("state", p.getState());
		jgen.writeStringField("zip", p.getZip());

//		jgen.writeFieldName("org");
//		if (org != null) {
//			jgen.writeStartObject();
//			jgen.writeNumberField("id", org.getId());
//			jgen.writeStringField("name", org.getName());
//			jgen.writeStringField("description", org.getDescription());
//			jgen.writeStringField("street", org.getStreet());
//			jgen.writeStringField("city", org.getCity());
//			jgen.writeStringField("state", org.getState());
//			jgen.writeStringField("zip", org.getZip());
//			jgen.writeEndObject();
//		} else {
//			jgen.writeNull();
//		}

		jgen.writeArrayFieldStart("friendships");
		for (Friendship friendship : p.getFriendships()) {
			jgen.writeObject(friendship);
		}
		jgen.writeEndArray();

		jgen.writeEndObject();
	}
}
