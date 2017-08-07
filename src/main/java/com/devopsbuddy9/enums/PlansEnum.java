package com.devopsbuddy9.enums;

/**
 * Created by kb on 7/21/2017.
 */
public enum PlansEnum {

    BASIC(1, "ROLE_BASIC"),
    PRO(2, "ROLE_PRO");

    private final int id;

    private final String planName;

    PlansEnum(int id, String roleName) {
        this.id=id;
        this.planName=roleName;
    }

    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }
}
