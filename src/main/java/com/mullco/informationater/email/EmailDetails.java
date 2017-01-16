package com.mullco.informationater.email;

import java.util.HashMap;
import java.util.Map;

public class EmailDetails {

    public static Map<String, String> getDigestEmailDefaults(String body) {
        HashMap<String, String> params = new HashMap<>();
        params.put("subject", "Members IT Update");
        params.put("to", "dmullins78@gmail.com,dmullins@fhlbdm.com");
        params.put("from", "dmullins7778@gmail.com");
        params.put("body", body);

        return params;
    }

}
