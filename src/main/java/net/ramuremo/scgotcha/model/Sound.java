package net.ramuremo.scgotcha.model;

public interface Sound {
    String artwork_url();

    String caption();

    boolean commentable();

    long comment_count();

    String created_at();

    String description();

    boolean downloadable();

    long download_count();

    long duration();

    long full_duration();

    String embeddable_by();

    String genre();

    boolean has_downloads_left();

    long id();

    String kind();

    String label_name();

    String last_modified();

    String license();

    long likes_count();

    String permalink();

    String permalink_url();

    long playback_count();

    boolean pub();

    PublisherMetadata publisherMetadata();

    String purchase_title();

    String purchase_url();

    String release_date();

    long reposts_count();

    String secret_token();

    String sharing();

    String state();

    boolean streamable();

    String tag_list();

    String title();

    String track_format();

    String uri();

    String urn();

    long user_id();

    Visuals visuals();

    String waveform_url();

    String display_date();

    Media media();

    String station_urn();

    String station_permalink();

    String track_authorization();

    String monetization_model();

    String policy();

    User user();
}