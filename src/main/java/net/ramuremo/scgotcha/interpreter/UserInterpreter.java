package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.Badges;
import net.ramuremo.scgotcha.model.CreatorSubscription;
import net.ramuremo.scgotcha.model.User;
import net.ramuremo.scgotcha.model.VisualsField;

public class UserInterpreter implements Interpreter<User> {

    @Override
    public User interpret(JsonObject json) {
        if (json == null) return null;
        return new User() {
            @Override
            public long id() {
                return json.get("id").getAsLong();
            }

            @Override
            public String urn() {
                return json.get("urn").getAsString();
            }

            @Override
            public String username() {
                return json.get("username").getAsString();
            }

            @Override
            public VisualsField visuals() {
                return new VisualsFieldInterpreter().interpret(json.get("visuals").getAsJsonObject());
            }

            @Override
            public Badges badges() {
                return null;
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
            public String url() {
                return json.get("url").getAsString();
            }

            @Override
            public String avatarUrl() {
                return json.get("avatar_url").getAsString();
            }

            @Override
            public String permalinkUrl() {
                return json.get("permalink_url").getAsString();
            }

            @Override
            public String uri() {
                return json.get("uri").getAsString();
            }

            @Override
            public String permalink() {
                return json.get("permalink").getAsString();
            }

            @Override
            public String lastModified() {
                return json.get("last_modified").getAsString();
            }

            @Override
            public String firstName() {
                return json.get("first_name").getAsString();
            }

            @Override
            public String fullName() {
                return json.get("full_name").getAsString();
            }

            @Override
            public String lastName() {
                return json.get("last_name").getAsString();
            }

            @Override
            public String description() {
                return json.get("description").getAsString();
            }

            @Override
            public String city() {
                return json.get("city").getAsString();
            }

            @Override
            public String countryCode() {
                return json.get("country_code").getAsString();
            }

            @Override
            public String createdAt() {
                return json.get("created_at").getAsString();
            }

            @Override
            public CreatorSubscription creatorSubscription() {
                return null;
            }


            @Override
            public long playlistCount() {
                return json.get("playlist_count").getAsLong();
            }

            @Override
            public long repostsCount() {
                return json.get("reposts_count").getAsLong();
            }

            @Override
            public long followersCount() {
                return json.get("followers_count").getAsLong();
            }

            @Override
            public long followingsCount() {
                return json.get("followings_count").getAsLong();
            }


            @Override
            public long commentsCount() {
                return json.get("comments_count").getAsLong();
            }

            @Override
            public long likesCount() {
                return json.get("likes_count").getAsLong();
            }

            @Override
            public long groupsCount() {
                return json.get("groups_count").getAsLong();
            }
        };
    }
}
