package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.PublisherMetadata;

public class PublisherMetadataInterpreter implements Interpreter<PublisherMetadata> {

    @Override
    public PublisherMetadata interpret(JsonObject json) {
        if (json == null) return null;
        return new PublisherMetadata() {
            @Override
            public long id() {
                return json.get("id").getAsLong();
            }

            @Override
            public String urn() {
                return json.get("urn").getAsString();
            }

            @Override
            public boolean containsMusic() {
                return json.get("contains_music").getAsBoolean();
            }
        };
    }
}
