package edu.sjsu.cmpe275.deepblue.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.sjsu.cmpe275.deepblue.model.Friendship;
import edu.sjsu.cmpe275.deepblue.model.Person;

public class FriendshipSerializer extends JsonSerializer<Friendship> {

	@Override
	public void serialize(Friendship friendship, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		Person person = friendship.getPerson();
		Person friend = friendship.getFriend();

		jgen.writeStartObject();
		jgen.writeStringField("status", friendship.getStatus().toString());

		/*
		 * "friendshipId" : { person: { "id": 1 }, friend: { "id": 2 } }
		 */
		jgen.writeFieldName("friendshipId");
		jgen.writeStartObject();

		jgen.writeFieldName("person");
		jgen.writeStartObject();
		jgen.writeNumberField("id", person.getId());
		jgen.writeStringField("firstname", person.getFirstname());
		jgen.writeStringField("lastname", person.getLastname());
		jgen.writeStringField("email", person.getEmail());
		jgen.writeEndObject();

		jgen.writeFieldName("friend");
		jgen.writeStartObject();
		jgen.writeNumberField("id", friend.getId());
		jgen.writeStringField("firstname", friend.getFirstname());
		jgen.writeStringField("lastname", friend.getLastname());
		jgen.writeStringField("email", friend.getEmail());
		jgen.writeEndObject();

		jgen.writeEndObject();

		jgen.writeEndObject();
	}
}
