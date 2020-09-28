package com.ht.constants;

public enum UserRole {
    SYS_ADMIN("SYS_ADMIN"),                       // 系统管理员
    GUEST_MANAGER("GUEST_MANAGER"),               // 客户经理
    ENTERPRISE_ADMIN("ENTERPRISE_ADMIN"),         // 企业管理员
    ENTERPRISE_OPERATOR("ENTERPRISE_OPERATOR"),   // 企业操作员
    ORG_ADMIN("ORG_ADMIN");                       // 机构管理员

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
