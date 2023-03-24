package org.pixellauncher.setting;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.geometry.Point2D;

import java.io.IOException;

public class Point2DDeserializer extends JsonDeserializer<Point2D> {

    @Override
    public Point2D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final double x = node.get("x").asDouble();
        final double y = node.get("y").asDouble();

        return new Point2D(x, y);
    }

}
