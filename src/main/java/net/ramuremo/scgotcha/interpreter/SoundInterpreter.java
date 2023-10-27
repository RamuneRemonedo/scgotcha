package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.*;

public class SoundInterpreter {
    private static final String JSON_STARTSWITH_PREFIX = "<script>window.__sc_hydration = ";

    public static Sound interpret(JsonObject json) {
        if (json == null) return null;
        if (validate(json)) throw new IllegalArgumentException("Invalid userHydratableJsonObject");
        JsonObject dataObject = json.get("data").getAsJsonObject();
        return new Sound() {
            @Override
            public String artworkUrl() {
                return dataObject.get("artwork_url").getAsString();
            }

            @Override
            public String caption() {
                return dataObject.get("caption").getAsString();
            }

            @Override
            public boolean commentable() {
                return dataObject.get("commentable").getAsBoolean();
            }

            @Override
            public long commentCount() {
                return dataObject.get("comment_count").getAsLong();
            }

            @Override
            public String createdAt() {
                return dataObject.get("created_at").getAsString();
            }

            @Override
            public String description() {
                return dataObject.get("description").getAsString();
            }

            @Override
            public boolean downloadable() {
                return dataObject.get("downloadable").getAsBoolean();
            }

            @Override
            public long downloadCount() {
                return dataObject.get("download_count").getAsLong();
            }

            @Override
            public long duration() {
                return dataObject.get("duration").getAsLong();
            }

            @Override
            public long fullDuration() {
                return dataObject.get("full_duration").getAsLong();
            }

            @Override
            public String embeddableBy() {
                return dataObject.get("embeddable_by").getAsString();
            }

            @Override
            public String genre() {
                return dataObject.get("genre").getAsString();
            }

            @Override
            public boolean hasDownloadsLeft() {
                return dataObject.get("has_downloads_left").getAsBoolean();
            }

            @Override
            public long id() {
                return dataObject.get("id").getAsLong();
            }

            @Override
            public String kind() {
                return dataObject.get("kind").getAsString();
            }

            @Override
            public String labelName() {
                return dataObject.get("label_name").getAsString();
            }

            @Override
            public String lastModified() {
                return dataObject.get("last_modified").getAsString();
            }

            @Override
            public String license() {
                return dataObject.get("license").getAsString();
            }

            @Override
            public long likesCount() {
                return dataObject.get("likes_count").getAsLong();
            }

            @Override
            public String permalink() {
                return dataObject.get("permalink").getAsString();
            }

            @Override
            public String permalinkUrl() {
                return dataObject.get("permalink_url").getAsString();
            }

            @Override
            public long playbackCount() {
                return dataObject.get("playback_count").getAsLong();
            }

            @Override
            public boolean pub() {
                return dataObject.get("pub").getAsBoolean();
            }

            @Override
            public PublisherMetadata publisherMetadata() {
                JsonObject publisherMetadataObject = dataObject.get("publisher_metadata").getAsJsonObject();
                return publisherMetadataObject == null ? null : PublisherMetadataInterpreter.interpret(publisherMetadataObject);
            }

            @Override
            public String purchaseTitle() {
                return dataObject.get("purchase_title").getAsString();
            }

            @Override
            public String purchaseUrl() {
                return dataObject.get("purchase_url").getAsString();
            }

            @Override
            public String releaseDate() {
                return dataObject.get("release_date").getAsString();
            }

            @Override
            public long repostsCount() {
                return dataObject.get("reposts_count").getAsLong();
            }

            @Override
            public String secretToken() {
                return dataObject.get("secret_token").getAsString();
            }

            @Override
            public String sharing() {
                return dataObject.get("sharing").getAsString();
            }

            @Override
            public String state() {
                return dataObject.get("state").getAsString();
            }

            @Override
            public boolean streamable() {
                return dataObject.get("streamable").getAsBoolean();
            }

            @Override
            public String tagList() {
                return dataObject.get("tag_list").getAsString();
            }

            @Override
            public String title() {
                return dataObject.get("title").getAsString();
            }

            @Override
            public String trackFormat() {
                return dataObject.get("track_format").getAsString();
            }

            @Override
            public String uri() {
                return dataObject.get("uri").getAsString();
            }

            @Override
            public String urn() {
                return dataObject.get("urn").getAsString();
            }

            @Override
            public long userId() {
                return dataObject.get("user_id").getAsLong();
            }

            @Override
            public VisualsField visuals() {
                return VisualsFieldInterpreter.interpret(dataObject.get("visuals").getAsJsonObject());
            }

            @Override
            public String waveformUrl() {
                return dataObject.get("waveform_url").getAsString();
            }

            @Override
            public String displayDate() {
                return dataObject.get("display_date").getAsString();
            }

            @Override
            public Media media() {
                JsonObject mediaObject = dataObject.get("media").getAsJsonObject();
                return new Media() {
                };
            }

            @Override
            public String stationUrn() {
                return dataObject.get("station_urn").getAsString();
            }

            @Override
            public String stationPermalink() {
                return dataObject.get("station_permalink").getAsString();
            }

            @Override
            public String trackAuthorization() {
                return dataObject.get("track_authorization").getAsString();
            }

            @Override
            public String monetizationModel() {
                return dataObject.get("monetization_model").getAsString();
            }

            @Override
            public String policy() {
                return dataObject.get("policy").getAsString();
            }

            @Override
            public User user() {
                JsonObject userObject = dataObject.get("user").getAsJsonObject();
                return userObject == null ? null : UserInterpreter.interpret(userObject);
            }
        };
    }

    public static boolean validate(JsonObject userHydratableJsonObject) {
        String hydratable = userHydratableJsonObject.get("hydratable").getAsString();
        return hydratable.equals("user");
    }
}
