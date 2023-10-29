package net.ramuremo.scgotcha.internal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import net.ramuremo.scgotcha.interpreter.PlaylistInterpreter;
import net.ramuremo.scgotcha.interpreter.SoundInterpreter;
import net.ramuremo.scgotcha.model.Media;
import net.ramuremo.scgotcha.model.Playlist;
import net.ramuremo.scgotcha.model.Sound;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

public class SoundCloudHandler {

    private static final String JSON_STARTSWITH_PREFIX = "<script>window.__sc_hydration = ";

    public static void saveSound(Sound sound) {
        try {
            fetchAndSaveMP3(sound);
            modifyMP3(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveSound(Playlist playlist) throws Exception {
        for (Sound sound : playlist.tracks()) {
            try {
                fetchAndSaveMP3(sound);
                modifyMP3(playlist, sound);
            } catch (NullPointerException e) {
                Sound fixedSound = fetchSoundByIdURL(URLFactory.createSoundIdUrl(sound.id()));
                fetchAndSaveMP3(fixedSound);
                modifyMP3(playlist, fixedSound);
            }
        }
    }

    private static void fetchAndSaveMP3(Sound sound) throws Exception {
        Media.Transcoding transcoding = sound.media().transcodings().stream()
                .filter(t -> t.format().mime_type().contains("mpeg") && t.format().protocol().equals("hls"))
                .findFirst()
                .orElse(null);
        String url = URLFactory.createMediaUrl(transcoding.url(), sound.trackAuthorization());
        String rawAudioUrl = fetchMediaRedirect(url);
        fetchAndSaveM3U(rawAudioUrl);
        convertM3UToMP3();
    }

    private static Sound fetchSound(String url) throws Exception {
        for (JsonElement element : fetchJsonByParmaURL(url)) {
            if (element.getAsJsonObject().get("hydratable").getAsString().equals("sound")) {
                return new SoundInterpreter().interpret(element.getAsJsonObject());
            }
        }
        return null;
    }

    private static void convertM3UToMP3() throws Exception {
        File mp3 = new File("./music/temp/music.mp3");
        if (mp3.exists()) mp3.delete();

        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-protocol_whitelist", "file,http,https,tcp,tls", "-i", "./music/temp/music.m3u", "./music/temp/music.mp3");
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

    private static String fetchMediaRedirect(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        String responseBody = new String(connection.getInputStream().readAllBytes());
        JsonObject json = new Gson().fromJson(responseBody, JsonObject.class);

        return json.get("url").getAsString();
    }

    private static File fetchAndSaveM3U(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        File dir = new File("./music/temp");
        File file = new File("./music/temp/music.m3u");
        dir.mkdirs();
        file.createNewFile();
        Files.write(file.toPath(), connection.getInputStream().readAllBytes());
        return file;
    }

    private static void modifyMP3(Playlist playlist, Sound sound) throws Exception {
        File tempAudio = new File("./music/temp/music.mp3");
        File dir = new File("./music/" + playlist.user().username() + "/" + playlist.title());
        dir.mkdirs();

        Mp3File audio = new Mp3File(tempAudio);
        ID3v2 tag = audio.getId3v2Tag();
        tag.setAlbumImage(fetchArtwork(sound), "image/jpeg");
        tag.setArtist(sound.user().username());
        tag.setAlbumArtist(playlist.user().username());
        tag.setTitle(sound.title());
        tag.setGenreDescription(sound.genre());
        tag.setAlbum(playlist.title());
        tag.setDate(sound.createdAt());
        audio.setId3v2Tag(tag);
        try {
            audio.save("./music/" + sound.user().username() + "/" + playlist.title() + "/" + sound.title() + ".mp3");
        } catch (InvalidPathException e) {
            File fixedDir = new File("./music/" + sound.user().username() + "/" + playlist.permalink());
            fixedDir.mkdirs();
            audio.save("./music/" + sound.user().username() + "/" + playlist.permalink() + "/" + sound.permalink() + ".mp3");
        }
    }

    private static void modifyMP3(Sound sound) throws Exception {
        File tempAudio = new File("./music/temp/music.mp3");
        File dir = new File("./music/" + sound.user().username());
        dir.mkdirs();

        Mp3File audio = new Mp3File(tempAudio);
        ID3v2 tag = audio.getId3v2Tag();
        tag.setAlbumImage(fetchArtwork(sound), "image/jpeg");
        tag.setArtist(sound.user().username());
        tag.setTitle(sound.title());
        tag.setGenreDescription(sound.genre());
        tag.setDate(sound.createdAt());
        audio.setId3v2Tag(tag);
        try {
            audio.save("./music/" + sound.user().username() + "/" + sound.title() + ".mp3");
        } catch (InvalidPathException e) {
            File fixedDir = new File("./music/" + toFixedPath(sound.user().username()));
            fixedDir.mkdirs();
            audio.save("./music/" + toFixedPath(sound.user().username()) + "/" + toFixedPath(sound.permalink()) + ".mp3");
        }
    }

    public static @Nullable Sound fetchSingle(String url) throws Exception {
        for (JsonElement element : fetchJsonByParmaURL(url)) {
            if (element.getAsJsonObject().get("hydratable").getAsString().equals("sound")) {
                return new SoundInterpreter().interpret(element.getAsJsonObject().get("data").getAsJsonObject());
            }
        }
        return null;
    }

    public static @Nullable Playlist fetchPlaylist(String url) throws Exception {
        for (JsonElement element : fetchJsonByParmaURL(url)) {
            if (element.getAsJsonObject().get("hydratable").getAsString().equals("playlist")) {
                return new PlaylistInterpreter().interpret(element.getAsJsonObject());
            }
        }
        return null;
    }

    private static JsonArray fetchJsonByParmaURL(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        String responseBody = new String(connection.getInputStream().readAllBytes());

        String json = "";
        for (String line : responseBody.split("\n")) {
            if (line.startsWith(JSON_STARTSWITH_PREFIX)) json = line.replace(JSON_STARTSWITH_PREFIX, "").replace(";</script>", "");
        }

        if (json.isBlank()) throw new RuntimeException("Could not find JSON in response body");

        return new Gson().fromJson(json, JsonArray.class);
    }

    public static Sound fetchSoundByIdURL(String url) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        String responseBody = new String(connection.getInputStream().readAllBytes());
        JsonObject json = new Gson().fromJson(responseBody, JsonObject.class);
        Sound sound = new SoundInterpreter().interpret(json);
        return sound;
    }

    public static byte[] fetchArtwork(Sound sound) throws Exception {
        URLConnection connection = new URL(sound.artworkUrl().replace("large.jpg", "t500x500.jpg")).openConnection();
        connection.connect();
        return connection.getInputStream().readAllBytes();
    }

    private static String toFixedPath(String path) {
        return path.replaceAll("[:*?\"<>|]", "_").replace("/", "-");
    }
}
