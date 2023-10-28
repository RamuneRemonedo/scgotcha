package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonObject;

public interface Interpreter<T> {
    T interpret(JsonObject json);
}
