package org.pixellauncher.setting.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class Point2D extends JsonDeserializer<javafx.geometry.Point2D> {

    @Override
    public javafx.geometry.Point2D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final double x = node.get("x").asDouble();
        final double y = node.get("y").asDouble();

        return new javafx.geometry.Point2D(x, y);
    }

}
