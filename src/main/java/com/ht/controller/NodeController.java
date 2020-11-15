package com.ht.controller;


import com.alibaba.fastjson.JSONObject;
import com.ht.common.JsonResponse;
import com.ht.constants.ResCode;
import com.ht.model.NodeInfo;
import com.ht.service.INodeinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/node")
public class NodeController {

    @Autowired
    INodeinfoService nodeinfoService;

    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject addNode(@RequestBody NodeInfo nodeInfo) {
        // TODO 检查node名称重复性
        if (nodeInfo.getNodeType() == null || nodeInfo.getNodeType() != "BD" || nodeInfo.getNodeType() != "ORG") {
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }

        int nodeId = nodeinfoService.addNode(nodeInfo);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("nodeId", nodeId);
        return resp.toJSON();


    }
}
