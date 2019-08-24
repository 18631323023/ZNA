package com.zna.server.service;


import com.zna.server.dao.UserDAO;
import com.zna.server.entity.bo.UserBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("userService")
@Transactional
public class UserService {
    @Resource
    private UserDAO userDAO;


    public void addUser(UserBO userBO){
        userDAO.addUser(userBO);
    }
}
