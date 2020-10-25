package com.ht.controller;


import com.alibaba.fastjson.JSONObject;
import com.ht.common.JsonResponse;
import com.ht.constants.ResCode;
import com.ht.model.EnterpriseInfo;
import com.ht.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    IEnterpriseService enterpriseService;

    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject addEnterprise(@RequestBody Map<String, String> map) {

        String enterpriseName = map.get("enterpriseName");
        String userId = map.get("userId");

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(enterpriseName) ){
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }

        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEnterpriseName(enterpriseName);
        int enterpriseId = enterpriseService.CreateEnterprise(userId,enterpriseInfo);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("enterpriseId", enterpriseId);
        return resp.toJSON();




    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getEnterpriseListByUser(@PathVariable String userId, @PathVariable String enterpriseName) {
        if(StringUtils.isEmpty(userId)){
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }

        String token = "";
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("token", token);
        return resp.toJSON();




    }
}
