package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.Media;

import java.util.ArrayList;
import java.util.List;

public class MediaInterpreter implements Interpreter<Media> {

    @Override
    public Media interpret(JsonObject json) {
        if (json == null) return null;
        return () -> {
            List<Media.Transcoding> transcodings = new ArrayList<>();
            for (JsonElement transcoding : json.get("transcodings").getAsJsonArray()) {
                System.out.println(transcoding);
                transcodings.add(
                        new TranscodingInterpreter().interpret(transcoding.getAsJsonObject())
                );
            }
            return transcodings;
        };
    }

    public static class TranscodingInterpreter implements Interpreter<Media.Transcoding> {

        @Override
        public Media.Transcoding interpret(JsonObject json) {
            if (json == null) return null;
            return new Media.Transcoding() {
                @Override
                public String url() {
                    return json.get("url").getAsString();
                }

                @Override
                public String preset() {
                    return json.get("preset").getAsString();
                }

                @Override
                public long duration() {
                    return json.get("duration").getAsLong();
                }

                @Override
                public boolean snipped() {
                    return json.get("snipped").getAsBoolean();
                }

                @Override
                public Format format() {
                    return new FormatInterpreter().interpret(json.getAsJsonObject("format"));
                }

                @Override
                public String quality() {
                    return json.get("quality").getAsString();
                }
            };
        }

        public static class FormatInterpreter implements Interpreter<Media.Transcoding.Format> {

            @Override
            public Media.Transcoding.Format interpret(JsonObject json) {
                if (json == null) return null;
                return new Media.Transcoding.Format() {
                    @Override
                    public String protocol() {
                        return json.get("protocol").getAsString();
                    }

                    @Override
                    public String mime_type() {
                        return json.get("mime_type").getAsString();
                    }
                };
            }
        }
    }
}
