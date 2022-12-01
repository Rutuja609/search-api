package com.search.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.search.beans.SearchTextCountBean;

public class SearchTextCountBeanSerializer extends JsonSerializer<SearchTextCountBean>{

	@Override
	public void serialize(SearchTextCountBean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField(value.getText(), value.getCount());
		gen.writeEndObject();
	}

}
