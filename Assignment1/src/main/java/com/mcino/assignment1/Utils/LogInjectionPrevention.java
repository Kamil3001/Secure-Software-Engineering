package com.mcino.assignment1.Utils;

import org.owasp.esapi.ESAPI;

public class LogInjectionPrevention {

    public static String makeSafe(String msg) {
        msg.replace('\n', '_')
                .replace('\r', '_')
                .replace('\t', '_');
        msg = ESAPI.encoder().encodeForHTML(msg);
        return msg;
    }
}
