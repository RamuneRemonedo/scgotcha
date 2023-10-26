package net.ramuremo.scgotcha.interpreter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.Sound;

public class SoundInterpreter {
    private static final String JSON_STARTSWITH_PREFIX = "<script>window.__sc_hydration = ";

    public static Sound interpret(JsonObject userHydratableJsonObject) {
        if (validate(userHydratableJsonObject)) throw new IllegalArgumentException("Invalid userHydratableJsonObject");
    }

    public static boolean validate(JsonObject userHydratableJsonObject) {
        String hydratable = userHydratableJsonObject.get("hydratable").getAsString();
        return hydratable.equals("user");
    }
}
