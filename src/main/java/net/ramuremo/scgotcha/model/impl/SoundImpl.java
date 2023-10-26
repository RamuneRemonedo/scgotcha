package net.ramuremo.scgotcha.model.impl;

import net.ramuremo.scgotcha.model.Sound;

public record SoundImpl(String artworkUrl, String createdAt, String description, long duration,
                        long fullDuration, String genre, long id, String lastModified, String license, long likesCount,
                        String permalink, String permalinkUrl, long playbackCount, long repostsCount, String title,
                        String trackFormat, String uri, String urn, long userId, String displayDate) implements Sound {

    public String artworkUrlT500() {
        return artworkUrl.replace("large", "t500x500");
    }
}
