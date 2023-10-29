package net.ramuremo.scgotcha;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.internal.SoundCloudHandler;
import net.ramuremo.scgotcha.interpreter.SoundInterpreter;
import net.ramuremo.scgotcha.model.Media;
import net.ramuremo.scgotcha.model.Playlist;
import net.ramuremo.scgotcha.model.Sound;

import java.util.List;


public class Main {
    private static final String JSON_STARTSWITH_PREFIX = "<script>window.__sc_hydration = ";

    public static void main(String[] args) throws Exception {
        List<String> urls = List.of(
                "https://soundcloud.com/geoxor/i-like-you",
                "https://soundcloud.com/geoxor/good-computers",
                "https://soundcloud.com/geoxor/only-now",
                "https://soundcloud.com/geoxor/dritic-let-you-go-geoxor-remix",
                "https://soundcloud.com/geoxor/turn-around",
                "https://soundcloud.com/geoxor/slot-machine",
                "https://soundcloud.com/geoxor/one-life-short",
                "https://soundcloud.com/geoxor/cheese",
                "https://soundcloud.com/geoxor/gotta-lay",
                "https://soundcloud.com/geoxor/you-i",
                "https://soundcloud.com/geoxor/your-eyes",
                "https://soundcloud.com/geoxor/poi",
                "https://soundcloud.com/geoxor/move",
                "https://soundcloud.com/geoxor/alone",
                "https://soundcloud.com/geoxor/geoxor-altimo-element",
                "https://soundcloud.com/geoxor/higher-free-download",
                "https://soundcloud.com/geoxor/lucid-dream",
                "https://soundcloud.com/geoxor/euphoria",
                "https://soundcloud.com/geoxor/nana",
                "https://soundcloud.com/geoxor/shaii",
                "https://soundcloud.com/geoxor/take-me-home",
                "https://soundcloud.com/geoxor/moonlight",
                "https://soundcloud.com/geoxor/stardust",
                "https://soundcloud.com/geoxor/restart",
                "https://soundcloud.com/geoxor/gloom",
                "https://soundcloud.com/geoxor/lollipop",
                "https://soundcloud.com/geoxor/virtual-arcadia-mix",
                "https://soundcloud.com/geoxor/virtual",
                "https://soundcloud.com/geoxor/sakura",
                "https://soundcloud.com/geoxor/twilight",
                "https://soundcloud.com/geoxor/world-is-mine",
                "https://soundcloud.com/geoxor/ephemeral",
                "https://soundcloud.com/geoxor/nandayo",
                "https://soundcloud.com/geoxor/bloodlust",
                "https://soundcloud.com/geoxor/aether",
                "https://soundcloud.com/geoxor/galaxy",
                "https://soundcloud.com/geoxor/faerie",
                "https://soundcloud.com/geoxor/moneko",
                "https://soundcloud.com/geoxor/kill-aura",
                "https://soundcloud.com/geoxor/dead",
                "https://soundcloud.com/geoxor/zenith"
                );
        for (String url : urls) {
            Sound sound = SoundCloudHandler.fetchSingle(url);
            SoundCloudHandler.saveSound(sound);
        }
    }

}