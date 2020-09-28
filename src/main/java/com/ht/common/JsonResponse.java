package com.ht.common;

import com.alibaba.fastjson.JSONObject;
import com.ht.constants.ResCode;

public class JsonResponse {
    private final ResCode resCode;
    private JSONObject data;

    public JsonResponse(ResCode resCode) {
        this.resCode = resCode;
    }

    public JsonResponse(ResCode resCode, JSONObject data) {
        this.resCode = resCode;
        this.data = data;
    }

    public void addData(String key, Object value) {
        if (null == this.data) {
            this.data = new JSONObject();
        }
        this.data.put(key, value);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("res_code", this.resCode.getCode());
        json.put("res_msg", this.resCode.getDesc());
        if (null != this.data) {
            json.put("data", this.data);
        }
        return json;
    }
}
