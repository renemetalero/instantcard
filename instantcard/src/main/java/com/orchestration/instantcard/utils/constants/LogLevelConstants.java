package com.orchestration.instantcard.utils.constants;

public enum LogLevelConstants {
    WARN("WARN"),
    INFO("INFO"),
    ERROR("ERROR"),

    CRITICAL("CRITICAL");

    private String text;

    LogLevelConstants(String text) { this.text = text; }

    public String getText() { return this.text; }
}
