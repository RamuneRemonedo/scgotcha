package net.ramuremo.scgotcha.interpreter;

import com.google.gson.JsonObject;
import net.ramuremo.scgotcha.model.Visuals;

public class VisualsInterpreter implements Interpreter<Visuals> {

    @Override
    public Visuals interpret(JsonObject json) {
        if (json == null) return null;
        return new Visuals() {
            @Override
            public String urn() {
                return json.get("urn").getAsString();
            }

            @Override
            public long entryTime() {
                return json.get("entry_time").getAsLong();
            }

            @Override
            public String visualUrl() {
                return json.get("visual_url").getAsString();
            }
        };
    }
}
