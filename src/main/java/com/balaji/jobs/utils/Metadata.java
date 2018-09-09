package com.balaji.jobs.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Metadata {

    public static final Map<String, Integer> JOBTYPES = new HashMap<String, Integer>() {{
        put(SCHEDULED, 1);
        put(ONDEMAND, 2);
    }};

    public static final Map<String, Integer> JOBSTATUS = new HashMap<String, Integer>() {{
        put(SUCCESS, 1);
        put(FAILED, 2);
    }};

    public static final String DAILY = "DAILY";

    public static final String MONTHLY = "MONTHLY";

    public static final String YEARLY = "YEARLY";

    public static final String SECOND = "SECONDS";

    public static final String MINUTES = "MINUTES";

    public static final String SCHEDULED = "SCHEDULED";

    public static final String ONDEMAND = "ONDEMAND";

    public static final String SUCCESS = "SUCCESS";

    public static final String FAILED = "FAILED";




    public static final Map<String, Integer> FREQS = new HashMap<String, Integer>() {{
        put(DAILY, 1);
        put(MONTHLY, 2);
        put(YEARLY, 3);
        put(MINUTES, 4);
        put(SECOND, 5);
    }};

    public static final String getFrequenctyTextFromId(int id) {
        Set<Map.Entry<String,Integer>> entries = FREQS.entrySet();
        for(Map.Entry<String,Integer> entry : entries) {
            if(entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }



}
