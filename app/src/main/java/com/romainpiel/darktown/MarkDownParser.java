package com.romainpiel.darktown;

public class MarkDownParser {
    static {
        System.loadLibrary("darktown");
    }

    public native String markdownToHtml(String raw);
}
