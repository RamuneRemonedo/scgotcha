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
        List<String> urls = List.of();
        for (String url : urls) {
            SoundCloudHandler.saveSound(SoundCloudHandler.fetchSingle(url));
        }
    }
}