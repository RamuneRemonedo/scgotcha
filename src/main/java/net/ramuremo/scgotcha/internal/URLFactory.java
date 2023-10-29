package net.ramuremo.scgotcha.internal;

public class URLFactory {
    public static String createMediaUrl(String transcodingUrl, String trackAuthorization) {
        return transcodingUrl
                + "?client_id=odn1E9M0osmPI1UsMDnFDuKcK5WSjS7s"
                + "&track_authorization=" + trackAuthorization;
    }

    public static String createSoundIdUrl(long soundId) {
        return "https://api-v2.soundcloud.com/tracks/"
                + soundId
                + "?client_id=odn1E9M0osmPI1UsMDnFDuKcK5WSjS7s";
    }
}
