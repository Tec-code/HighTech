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

import java.util.List;
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
        int userId = Integer.valueOf(map.get("userId"));

        if (userId == 0 || StringUtils.isEmpty(enterpriseName)) {
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }

        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEnterpriseName(enterpriseName);
        int enterpriseId = enterpriseService.CreateEnterprise(userId, enterpriseInfo);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("enterpriseId", enterpriseId);
        return resp.toJSON();


    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getEnterpriseListByUser(@PathVariable Integer userId, @PathVariable String enterpriseName) {
        if (userId == 0) {
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }

        List<EnterpriseInfo> enterpriseInfoLit = enterpriseService.GetEnterpriseList(userId,enterpriseName);

        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("enterpriseInfoLit", enterpriseInfoLit);
        return resp.toJSON();


    }
}
