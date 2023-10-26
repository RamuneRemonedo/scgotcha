package net.ramuremo.scgotcha.model.impl;

import net.ramuremo.scgotcha.model.AbstractSound;

public record AbstractSoundImpl(String userPermalink, String soundPermalink) implements AbstractSound {
}
