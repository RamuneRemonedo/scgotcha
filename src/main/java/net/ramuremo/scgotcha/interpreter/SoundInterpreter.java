package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.*;

public class SoundInterpreter implements Interpreter<Sound> {
    
    @Override
    public Sound interpret(JsonObject json) {
        if (json == null) return null;
        return new Sound() {
            @Override
            public String artworkUrl() {
                return json.get("artwork_url").getAsString();
            }

            @Override
            public String caption() {
                return json.get("caption").getAsString();
            }

            @Override
            public boolean commentable() {
                return json.get("commentable").getAsBoolean();
            }

            @Override
            public long commentCount() {
                return json.get("comment_count").getAsLong();
            }

            @Override
            public String createdAt() {
                return json.get("created_at").getAsString();
            }

            @Override
            public String description() {
                return json.get("description").getAsString();
            }

            @Override
            public boolean downloadable() {
                return json.get("downloadable").getAsBoolean();
            }

            @Override
            public long downloadCount() {
                return json.get("download_count").getAsLong();
            }

            @Override
            public long duration() {
                return json.get("duration").getAsLong();
            }

            @Override
            public long fullDuration() {
                return json.get("full_duration").getAsLong();
            }

            @Override
            public String embeddableBy() {
                return json.get("embeddable_by").getAsString();
            }

            @Override
            public String genre() {
                return json.get("genre").getAsString();
            }

            @Override
            public boolean hasDownloadsLeft() {
                return json.get("has_downloads_left").getAsBoolean();
            }

            @Override
            public long id() {
                return json.get("id").getAsLong();
            }

            @Override
            public String kind() {
                return json.get("kind").getAsString();
            }

            @Override
            public String labelName() {
                return json.get("label_name").getAsString();
            }

            @Override
            public String lastModified() {
                return json.get("last_modified").getAsString();
            }

            @Override
            public String license() {
                return json.get("license").getAsString();
            }

            @Override
            public long likesCount() {
                return json.get("likes_count").getAsLong();
            }

            @Override
            public String permalink() {
                return json.get("permalink").getAsString();
            }

            @Override
            public String permalinkUrl() {
                return json.get("permalink_url").getAsString();
            }

            @Override
            public long playbackCount() {
                return json.get("playback_count").getAsLong();
            }

            @Override
            public boolean pub() {
                return json.get("pub").getAsBoolean();
            }

            @Override
            public PublisherMetadata publisherMetadata() {
                JsonObject publisherMetajson = json.get("publisher_metadata").getAsJsonObject();
                return publisherMetajson == null ? null : new PublisherMetadataInterpreter().interpret(publisherMetajson);
            }

            @Override
            public String purchaseTitle() {
                return json.get("purchase_title").getAsString();
            }

            @Override
            public String purchaseUrl() {
                return json.get("purchase_url").getAsString();
            }

            @Override
            public String releaseDate() {
                return json.get("release_date").getAsString();
            }

            @Override
            public long repostsCount() {
                return json.get("reposts_count").getAsLong();
            }

            @Override
            public String secretToken() {
                return json.get("secret_token").getAsString();
            }

            @Override
            public String sharing() {
                return json.get("sharing").getAsString();
            }

            @Override
            public String state() {
                return json.get("state").getAsString();
            }

            @Override
            public boolean streamable() {
                return json.get("streamable").getAsBoolean();
            }

            @Override
            public String tagList() {
                return json.get("tag_list").getAsString();
            }

            @Override
            public String title() {
                return json.get("title").getAsString();
            }

            @Override
            public String trackFormat() {
                return json.get("track_format").getAsString();
            }

            @Override
            public String uri() {
                return json.get("uri").getAsString();
            }

            @Override
            public String urn() {
                return json.get("urn").getAsString();
            }

            @Override
            public long userId() {
                return json.get("user_id").getAsLong();
            }

            @Override
            public VisualsField visuals() {
                return new VisualsFieldInterpreter().interpret(json.get("visuals").getAsJsonObject());
            }

            @Override
            public String waveformUrl() {
                return json.get("waveform_url").getAsString();
            }

            @Override
            public String displayDate() {
                return json.get("display_date").getAsString();
            }

            @Override
            public Media media() {
                System.out.println(json);
                JsonObject mediaObject = json.get("media").getAsJsonObject();
                return new MediaInterpreter().interpret(mediaObject);
            }

            @Override
            public String stationUrn() {
                return json.get("station_urn").getAsString();
            }

            @Override
            public String stationPermalink() {
                return json.get("station_permalink").getAsString();
            }

            @Override
            public String trackAuthorization() {
                return json.get("track_authorization").getAsString();
            }

            @Override
            public String monetizationModel() {
                return json.get("monetization_model").getAsString();
            }

            @Override
            public String policy() {
                return json.get("policy").getAsString();
            }

            @Override
            public User user() {
                JsonObject userObject = json.get("user").getAsJsonObject();
                return userObject == null ? null : new UserInterpreter().interpret(userObject);
            }
        };
    }
}
