package com.mcino.assignment1.Utils;

public class LogInjectionPrevention {

    public static String makeSafe(String msg) {
        msg.replace('\n', '_')
                .replace('\r', '_')
                .replace('\t', '_');
        return msg;
    }
}
