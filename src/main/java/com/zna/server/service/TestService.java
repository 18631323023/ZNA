package com.zna.server.service;

import com.zna.server.dao.TestDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service("testService")
@Transactional
public class TestService {
    @Resource
    private TestDAO testDAO;




   public int  test() {
        return 0;
    }
}
