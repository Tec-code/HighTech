package com.ht.model;

import java.util.Date;

public class EnterpriseDeal {

    private Integer userId;

    private String enterpriseName;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

}
