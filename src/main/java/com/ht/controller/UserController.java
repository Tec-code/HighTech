package com.ht.controller;

import com.alibaba.fastjson.JSONObject;
import com.ht.common.JsonResponse;
import com.ht.common.auth.TokenProvider;
import com.ht.constants.ResCode;
import com.ht.model.UserInfo;
import com.ht.service.IUserService;
import com.ht.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String SESSION_VERIFY_CODE_KEY = "verifyCode";
    private static final String SESSION_ERROR_COUNT_KEY = "errCount";
    private static final int TRY_COUNTS_BEFORE_VCODE = 5;

    @Autowired
    IUserService userService;

    @Autowired
    TokenProvider tokenProvider;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject register(@RequestBody UserInfo userInfo) {
        String userNo = userInfo.getUserNo();
        String password = userInfo.getPassword();

        if (StringUtils.isEmpty(userNo) || StringUtils.isEmpty(password)) {
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }
        // TODO check userNo/password...

        UserInfo userInDB = userService.getUserByUserNo(userNo);
        if (null != userInDB) {
            return new JsonResponse(ResCode.USERNO_EXIST).toJSON();
        }

        if (userService.registerUser(userInfo)) {
            return new JsonResponse(ResCode.OK).toJSON();
        }
        return new JsonResponse(ResCode.REGISTER_FAILED).toJSON();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody Map<String, String> map, HttpServletRequest request) {

        String userNo = map.get("userNo");
        String password = map.get("password");
        String verifyCode = map.get("verifyCode");

        if (StringUtils.isEmpty(userNo) || StringUtils.isEmpty(password)) {
            return new JsonResponse(ResCode.REQUEST_ERROR).toJSON();
        }

        HttpSession session = request.getSession();

        // 验证码校验
        ResCode resCode = checkVerifyCode(session, verifyCode);
        if (null != resCode) {
            return new JsonResponse(resCode).toJSON();
        }

        // 用户名密码校验
        UserInfo userInDB = userService.getUserByUserNo(userNo);
        if (null == userInDB || !userService.checkUserPassword(userInDB, password)) {
            // 错误次数 +1
            Object errorCount = session.getAttribute(SESSION_ERROR_COUNT_KEY);
            int count = null == errorCount? 1 : Integer.parseInt(errorCount.toString()) + 1;
            session.setAttribute(SESSION_ERROR_COUNT_KEY, count);

            JsonResponse response = new JsonResponse(ResCode.USERNO_PASSWORD_ERROR);
            // 超过限定次数，需要输入验证码
            if (count >= TRY_COUNTS_BEFORE_VCODE) {
                response.addData("verifyCodeRequired", true);
            }
            return response.toJSON();
        }

        // 认证通过，错误次数清零
        session.setAttribute(SESSION_ERROR_COUNT_KEY, 0);

        // 生成 token
        String token = tokenProvider.getToken(userInDB);
        JsonResponse resp = new JsonResponse(ResCode.OK);
        resp.addData("token", token);
        return resp.toJSON();
    }

    private ResCode checkVerifyCode(HttpSession session, String verifyCode) {
        Object errorCount = session.getAttribute(SESSION_ERROR_COUNT_KEY);
        if (null != errorCount && Integer.parseInt(errorCount.toString()) >= TRY_COUNTS_BEFORE_VCODE) {
            if (StringUtils.isEmpty(verifyCode)) {
                return ResCode.VERIFY_CODE_REQUIRED;
            }
            Object vcode = session.getAttribute(SESSION_VERIFY_CODE_KEY);
            if (null == vcode || !verifyCode.equals(vcode.toString())) {
                return ResCode.VERIFY_CODE_ERROR;
            }
        }
        return null;
    }

    @RequestMapping(value = "/verify-code", method = RequestMethod.GET, produces = "image/png")
    @ResponseBody
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 利用图片工具生成图片
        // 返回的数组第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyCodeUtil.newBuilder().build().createImage();

        // 将验证码存入Session
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_VERIFY_CODE_KEY, objs[0]);

        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getUserNameById(@PathVariable int userId) {
        return userService.getUserById(userId).getUserName();
    }
}
