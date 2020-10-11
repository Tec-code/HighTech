package com.ht.constants;

public enum UserStatus {
    NORMAL("normal"),     // 在职
    QUIT("quit"),         // 离职
    HANDOVER("handover"); // 交接

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
