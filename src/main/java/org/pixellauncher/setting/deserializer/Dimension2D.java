package org.pixellauncher.setting.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class Dimension2D extends JsonDeserializer<javafx.geometry.Dimension2D> {

    @Override
    public javafx.geometry.Dimension2D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final double width = node.get("width").asDouble();
        final double height = node.get("height").asDouble();

        return new javafx.geometry.Dimension2D(width, height);
    }

}
