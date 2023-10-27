package net.ramuremo.scgotcha.model;

import java.util.List;

public interface VisualsField {
    String urn();
    boolean enabled();
    List<Visuals> visuals();
    String tracking();
}
