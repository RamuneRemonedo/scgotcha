package net.ramuremo.scgotcha;

import com.github.manevolent.ffmpeg4j.FFmpegIO;
import com.github.manevolent.ffmpeg4j.FFmpegInput;
import com.github.manevolent.ffmpeg4j.stream.source.FFmpegSourceStream;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.interpreter.SoundInterpreter;
import net.ramuremo.scgotcha.model.Media;
import net.ramuremo.scgotcha.model.Sound;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class Main {
    private static final String JSON_STARTSWITH_PREFIX = "<script>window.__sc_hydration = ";

    public static void main(String[] args) throws Exception {
        URLConnection connection = new URL("https://soundcloud.com/kamomesano/templimeneonlightkamome").openConnection();
        connection.connect();
        String responseBody = new String(connection.getInputStream().readAllBytes());
        String json = "";
        for (String line : responseBody.split("\n")) {
            if (line.startsWith(JSON_STARTSWITH_PREFIX)) json = line.replace(JSON_STARTSWITH_PREFIX, "").replace(";</script>", "");
        }

        JsonArray jsonElements = new Gson().fromJson(json, JsonArray.class);
        for (JsonElement jsonElement : jsonElements) {
            if (jsonElement.getAsJsonObject().get("hydratable").getAsString().equals("sound")) {
                Sound sound = SoundInterpreter.interpret(jsonElement.getAsJsonObject());
                Media.Transcoding transcoding = sound.media().transcodings().stream()
                        .filter(t -> t.format().mime_type().contains("mpeg") && t.format().protocol().equals("hls"))
                        .findFirst()
                        .orElse(null);
                String url = transcoding.url() + "?client_id=odn1E9M0osmPI1UsMDnFDuKcK5WSjS7s"
                        + "&track_authorization=" + sound.trackAuthorization();

                String redirectUrl = fetchMediaRedirect(url);
                File file = fetchAndSaveM3U(redirectUrl);
                convertM3UToMP3(file);
            }
        }
    }

    public static String fetchMediaRedirect(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        String responseBody = new String(connection.getInputStream().readAllBytes());
        JsonObject json = new Gson().fromJson(responseBody, JsonObject.class);

        return json.get("url").getAsString();
    }

    public static File fetchAndSaveM3U(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        File file = new File("./output.m3u");
        Files.write(file.toPath(), connection.getInputStream().readAllBytes());
        return file;
    }

    public static void convertM3UToMP3(File file) throws Exception {
        File mp3 = new File("./music/output.mp3");
        if (mp3.exists()) mp3.delete();

        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-protocol_whitelist", "file,http,https,tcp,tls", "-i", file.getName(), "output.wav");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        Thread outputThread = new Thread(() -> {
            try {
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        outputThread.start();
        process.waitFor();
        outputThread.join();
    }
}