package net.ramuremo.scgotcha.model;

import java.util.List;

public interface Playlist {
    String artworkUrl();
    String createdAt();
    String description();
    long duration();
    boolean embeddableBy();
    String genre();
    long id();
    String labelName();
    String lastModified();
    String license();
    long likesCount();
    boolean managedByFeeds();
    String permalink();
    String permalinkUrl();
    boolean pub();
    String purchaseTitle();
    String purchaseUrl();
    String releaseDate();
    long repostsCount();
    String secretToken();
    String sharing();
    String tagList();
    String title();
    String uri();
    long userId();
    String setType();
    boolean isAlbum();
    String publishedAt();
    String displayDate();
    User user();
    List<Sound> tracks();
    long trackCount();
    String url();
}
