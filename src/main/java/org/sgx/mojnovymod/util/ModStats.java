package org.sgx.mojnovymod.util;

public class ModStats {
    public static final String OMIN_RADIATE = "omin_radiate";
    public static final int OMIN_RADIATE_MAX = 10;

    public static int getMaxStatsValue(String id) {
        if(id.equals(OMIN_RADIATE)) return OMIN_RADIATE_MAX;
        return 0;
    }
}