package org.pixellauncher.setting;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.geometry.Dimension2D;

import java.io.IOException;

public class Dimension2DDeserializer extends JsonDeserializer<Dimension2D> {

    @Override
    public Dimension2D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = p.getCodec().readTree(p);
        final double width = node.get("width").asDouble();
        final double height = node.get("height").asDouble();

        return new Dimension2D(width, height);
    }

}
