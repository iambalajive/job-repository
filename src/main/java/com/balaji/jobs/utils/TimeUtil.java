package com.balaji.jobs.utils;

public class TimeUtil {

    public static long getNextExecutionTimeinMillis(String freqType, int freqVal) {

        if (freqType.equals(Metadata.SECOND))
            return System.currentTimeMillis() + freqVal * 1000;
        else if (freqType.equals(Metadata.MINUTES))
            return System.currentTimeMillis() + freqVal * 60 * 1000;
        else
            return -1;
    }
}

