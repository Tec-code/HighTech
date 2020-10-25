package com.ht.constants;

public enum EnterpriseUserRelaType {
    // relatype
    ENTERPRISE_OPERATOR("ENTERPRISE_OPERATOR"),
    GUEST_MANAGER("GUEST_MANAGER"),
    ENTERPRISE_ADMIN("ENTERPRISE_ADMIN"),

    // relaStatus
    NORMAL("normal"),
    HANDOVER("handover");


    private final String relaType;

    EnterpriseUserRelaType(String relaType) {
        this.relaType = relaType;
    }

    public String getEnterpriseUserRelaType() {
        return relaType;
    }
}
