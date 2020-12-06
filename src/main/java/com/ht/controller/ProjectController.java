package com.ht.controller;

import com.alibaba.fastjson.JSONObject;
import com.ht.common.JsonResponse;
import com.ht.constants.ResCode;
import com.ht.model.*;
import com.ht.service.IEnterpriseProjectService;
import com.ht.service.IEnterpriseService;
import com.ht.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private static final String PROJECT_STATUS_INIT = "init";// 初始化
    private static final String PROJECT_STATUS_ONGOING = "ongoing";// 进行中
    private static final String PROJECT_STATUS_FINISH = "finish";// 完结

    @Autowired
    IEnterpriseProjectService enterpriseProjectService;

    @Autowired
    IEnterpriseService enterpriseService;

    @Autowired
    IProjectService projectService;

    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject addProject(@RequestBody Map<String, String> map) {

        String projectName = map.get("projectName");
        int enterpriseId = Integer.valueOf(map.get("enterpriseId"));
        String projectId = map.get("projectId");

        if (enterpriseId == 0) {
            return new JsonResponse(ResCode.ENTERPRISE_ID_NULL).toJSON();
        }

        String regex = "^[a-zA-Z0-9\\\\W_!@#$%^&*`~()-+=]{1,20}$";
        if (!projectId.matches(regex)) {
            return new JsonResponse(ResCode.PROJECT_ID_FORMAT_NOT_MATCH).toJSON();
        }
        if (StringUtils.isEmpty(projectName)) {
            return new JsonResponse(ResCode.PROJECT_NAME_NULL).toJSON();
        }

        EnterpriseProject enterpriseProject = new EnterpriseProject();
        enterpriseProject.setEnterpriseId(enterpriseId);
        enterpriseProject.setProjectName(projectName);
        enterpriseProject.setProjectId(projectId);
        enterpriseProject.setProjectStatus(PROJECT_STATUS_INIT);

        // 校验企业ID是否存在
        EnterpriseInfo enterpriseInfo = enterpriseService.getEnterpriseById(enterpriseProject.getEnterpriseId());
        if (null == enterpriseInfo) {
            return new JsonResponse(ResCode.ENTERPRISE_NOT_EXIST).toJSON();
        }

        enterpriseProjectService.createProject(enterpriseProject);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("projectId", projectId);
        return resp.toJSON();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject getProjectListByName(@RequestParam("projectName") String projectName) {

        List<EnterpriseProject> projectList = enterpriseProjectService.getProjectList(projectName);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("total", projectList.size());
        resp.addData("projectList", projectList);
        return resp.toJSON();
    }

    @RequestMapping(value = "/info", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject deleteProjectById(@RequestParam("projectId") String projectId) {

        if (projectId == null || projectId.isEmpty()) {
            return new JsonResponse(ResCode.PROJECT_ID_NULL).toJSON();
        }
        enterpriseProjectService.deleteProject(projectId);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("projectId", projectId);
        return resp.toJSON();
    }

    @RequestMapping(value = "/info", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject deleteProjectById(@RequestBody Map<String, String> map) {

        String projectName = map.get("projectName");
        if (projectName == null || projectName.isEmpty()) {
            return new JsonResponse(ResCode.PROJECT_NAME_NULL).toJSON();
        }
        // TODO 项目名称规则校验

        String projectId = map.get("projectId");

        String regex = "^[a-zA-Z0-9\\\\W_!@#$%^&*`~()-+=]{1,20}$";
        if (!projectId.matches(regex)) {
            return new JsonResponse(ResCode.PROJECT_ID_FORMAT_NOT_MATCH).toJSON();
        }
   /*
        // 校验项目id是否为空
        if (projectId == null || projectId.isEmpty()) {
            return new JsonResponse(ResCode.PROJECT_ID_NULL).toJSON();
        }*/

        // 校验项目id是否存在
        EnterpriseProject en = enterpriseProjectService.getProject(projectId);
        if (null == en) {
            return new JsonResponse(ResCode.PROJECT_ID_NOT_EXIST).toJSON();
        }
        EnterpriseProject enterpriseProject = new EnterpriseProject();
        enterpriseProject.setProjectName(projectName);
        enterpriseProject.setProjectId(projectId);
        enterpriseProject.setUpdateTime(new Date());
        enterpriseProjectService.updateProject(enterpriseProject);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("projectId", projectId);
        return resp.toJSON();
    }


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
