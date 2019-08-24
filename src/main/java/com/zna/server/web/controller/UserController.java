package com.zna.server.web.controller;


import com.zna.server.entity.bo.AdminBO;
import com.zna.server.entity.bo.UserBO;
import com.zna.server.entity.dto.ResultDTOBuilder;
import com.zna.server.service.UserService;
import com.zna.server.util.JsonUtils;
import com.zna.server.util.StringUtils;
import com.zna.server.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @RequestMapping("/add")
    public void addUser(String name,Integer age,String gender,HttpServletRequest request,HttpServletResponse response){
        try {
            log.info(request.getRequestURI());
            log.info("param:{}", JsonUtils.getJsonString4JavaPOJO(request.getParameterMap()));
            //获取管理员对象
            AdminBO loginAdmin = super.getLoginAdmin(request);
            log.info("user{}",loginAdmin);
//            if (loginAdmin==null){
//                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
//                super.safeJsonPrint(response, result);
//                log.info("result{}",result);
//                return ;
//            }
            //参数验证
            if (StringUtils.isEmpty(name)||age==null){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                super.safeJsonPrint(response, result);
                log.info("result{}",result);
                return ;
            }
            UserBO userBO = new UserBO();
            userBO.setName(name);
            userBO.setAge(age);
            userBO.setGender(gender);
            userService.addUser(userBO);


                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功!"));
                super.safeJsonPrint(response, result);
                log.info("result{}", result);
                return;

        }catch (Exception e){
            e.getStackTrace();
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeJsonPrint(response, result);
            log.error("addMemberException",e);
        }
    }
    }






