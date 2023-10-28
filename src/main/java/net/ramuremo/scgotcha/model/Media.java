package net.ramuremo.scgotcha.model;

import java.util.List;

public interface Media {
    List<Transcoding> transcodings();
    interface Transcoding {
        String url();
        String preset();
        long duration();
        boolean snipped();
        Format format();
        String quality();

        interface Format {
            String protocol();
            String mime_type();
        }
    }
}
