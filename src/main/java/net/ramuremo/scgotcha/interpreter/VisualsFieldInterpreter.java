package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.Visuals;
import net.ramuremo.scgotcha.model.VisualsField;

import java.util.ArrayList;
import java.util.List;

public class VisualsFieldInterpreter {
    public static VisualsField interpret(JsonObject json) {
        if (json == null) return null;
        return new VisualsField() {
            @Override
            public String urn() {
                return json.get("urn").getAsString();
            }

            @Override
            public boolean enabled() {
                return json.get("enabled").getAsBoolean();
            }

            @Override
            public List<Visuals> visuals() {
                List<Visuals> visuals = new ArrayList<>();
                for (JsonElement element : json.get("visuals").getAsJsonArray()) {
                    visuals.add(VisualsInterpreter.interpret(element.getAsJsonObject()));
                }
                return visuals;
            }

            @Override
            public String tracking() {
                return json.get("tracking").getAsString();
            }
        };
    }
}
