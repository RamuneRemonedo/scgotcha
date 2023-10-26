package net.ramuremo.scgotcha.model;

import net.ramuremo.scgotcha.Badges;

public interface User {
    String avatarUrl();
    String city();
    long commentsCount();
    String countryCode();
    String createdAt();
    CreatorSubscription creatorSubscription();
    String description();
    long followersCount();
    long followingsCount();
    String firstName();
    String fullName();
    long groupsCount();
    long id();
    String lastModified();
    String lastName();
    long likesCount();
    String permalink();
    String permalinkUrl();
    long playlistCount();
    long repostsCount();
    String uri();
    String urn();
    String username();
    Visuals visuals();
    Badges badges();
    String stationUrn();
    String stationPermalink();
    String url();
}
