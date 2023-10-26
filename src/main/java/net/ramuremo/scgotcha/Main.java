package net.ramuremo.scgotcha;

import net.ramuremo.scgotcha.interpreter.SoundInterpreter;

import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws Exception {
        URLConnection connection = new URL("https://soundcloud.com/djkelcore/dress").openConnection();
        connection.connect();
        // print body
        String responseBody = new String(connection.getInputStream().readAllBytes());
        SoundInterpreter.interpret(responseBody);
    }
}