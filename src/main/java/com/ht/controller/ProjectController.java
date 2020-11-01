package com.ht.controller;

import com.alibaba.fastjson.JSONObject;
import com.ht.common.JsonResponse;
import com.ht.constants.ResCode;
import com.ht.model.EnterpriseProjectApproval;
import com.ht.model.EnterpriseProjectBasic;
import com.ht.model.EnterpriseProjectFee;
import com.ht.service.IEnterpriseService;
import com.ht.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @RequestMapping(value = "/feelist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getProjectTypeFee(@RequestParam("projectId") String projectId, @RequestParam("monthId") String monthId, @RequestParam("feeType") String feeType, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {


        List<EnterpriseProjectFee> enterpriseProjectFeeList = projectService.getProjectFee(projectId, monthId, feeType, offset, limit);
        int feeCount = projectService.getCount(projectId, monthId, feeType);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("enterpriseInfoLit", enterpriseProjectFeeList);
        resp.addData("total", feeCount);
        return resp.toJSON();
    }

    @RequestMapping(value = "/approval", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject modifyApproval(@RequestBody EnterpriseProjectApproval enterpriseProjectApproval) {


        projectService.modifyProjectApproval(enterpriseProjectApproval);

        JsonResponse resp = new JsonResponse(ResCode.OK);
        return resp.toJSON();
    }

    @RequestMapping(value = "/approval", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject queryProjectApproval(@RequestParam("projectId") String projectId) {


        EnterpriseProjectApproval enterpriseProjectApproval = projectService.queryProjectApproval(projectId);

        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("enterpriseProjectApproval", enterpriseProjectApproval);
        return resp.toJSON();
    }

    @RequestMapping(value = "/basic", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject modifyBasic(@RequestBody EnterpriseProjectBasic enterpriseProjectBasic) {


        projectService.modifyProjectBasic(enterpriseProjectBasic);

        JsonResponse resp = new JsonResponse(ResCode.OK);
        return resp.toJSON();
    }

    @RequestMapping(value = "/basic", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject queryProjectBasic(@RequestParam("projectId") String projectId) {


        EnterpriseProjectBasic enterpriseProjectBasic = projectService.queryProjectBasic(projectId);

        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("enterpriseProjectBasic", enterpriseProjectBasic);
        return resp.toJSON();
    }
}
