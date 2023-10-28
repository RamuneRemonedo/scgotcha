package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.Playlist;
import net.ramuremo.scgotcha.model.Sound;
import net.ramuremo.scgotcha.model.User;

import java.util.ArrayList;
import java.util.List;

public class PlaylistInterpreter implements Interpreter<Playlist> {

    @Override
    public Playlist interpret(JsonObject json) {
        if (json == null) return null;
        JsonObject data = json.get("data").getAsJsonObject();
        return new Playlist() {
            @Override
            public String artworkUrl() {
                return data.get("artwork_url").getAsString();
            }

            @Override
            public String createdAt() {
                return data.get("created_at").getAsString();
            }

            @Override
            public String description() {
                return data.get("description").getAsString();
            }

            @Override
            public long duration() {
                return data.get("duration").getAsLong();
            }

            @Override
            public boolean embeddableBy() {
                return data.get("embeddable_by").getAsBoolean();
            }

            @Override
            public String genre() {
                return data.get("genre").getAsString();
            }

            @Override
            public long id() {
                return data.get("id").getAsLong();
            }

            @Override
            public String labelName() {
                return data.get("label_name").getAsString();
            }

            @Override
            public String lastModified() {
                return data.get("last_modified").getAsString();
            }

            @Override
            public String license() {
                return data.get("license").getAsString();
            }

            @Override
            public long likesCount() {
                return data.get("likes_count").getAsLong();
            }

            @Override
            public boolean managedByFeeds() {
                return data.get("managed_by_feeds").getAsBoolean();
            }

            @Override
            public String permalink() {
                return data.get("permalink").getAsString();
            }

            @Override
            public String permalinkUrl() {
                return data.get("permalink_url").getAsString();
            }

            @Override
            public boolean pub() {
                return data.get("pub").getAsBoolean();
            }

            @Override
            public String purchaseTitle() {
                return data.get("purchase_title").getAsString();
            }

            @Override
            public String purchaseUrl() {
                return data.get("purchase_url").getAsString();
            }

            @Override
            public String releaseDate() {
                return data.get("release_date").getAsString();
            }

            @Override
            public long repostsCount() {
                return data.get("reposts_count").getAsLong();
            }

            @Override
            public String secretToken() {
                return data.get("secret_token").getAsString();
            }

            @Override
            public String sharing() {
                return data.get("sharing").getAsString();
            }

            @Override
            public String tagList() {
                return data.get("tag_list").getAsString();
            }

            @Override
            public String title() {
                return data.get("title").getAsString();
            }

            @Override
            public String uri() {
                return data.get("uri").getAsString();
            }

            @Override
            public long userId() {
                return data.get("user_id").getAsLong();
            }

            @Override
            public String setType() {
                return data.get("set_type").getAsString();
            }

            @Override
            public boolean isAlbum() {
                return data.get("is_album").getAsBoolean();
            }

            @Override
            public String publishedAt() {
                return data.get("published_at").getAsString();
            }

            @Override
            public String displayDate() {
                return data.get("display_date").getAsString();
            }

            @Override
            public User user() {
                return new UserInterpreter().interpret(data.getAsJsonObject("user"));
            }

            @Override
            public List<Sound> tracks() {
                System.out.println(data);
                JsonArray jsonElements = data.getAsJsonArray("tracks");
                List<Sound> tracks = new ArrayList<>();
                jsonElements.forEach(jsonElement -> tracks.add(new SoundInterpreter().interpret(jsonElement.getAsJsonObject())));
                return tracks;
            }

            @Override
            public long trackCount() {
                return data.get("track_count").getAsLong();
            }

            @Override
            public String url() {
                return data.get("url").getAsString();
            }
        };
    }
}
