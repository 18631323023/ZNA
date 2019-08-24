package com.zna.server.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ShearCaptcha;

public class TestUtil {
    public static void main(String[] args) {
        // 定义图形验证码的长和宽
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(300,150,5,5);
        // CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
        // 图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/captcha/circle222.png");
        // 验证图形验证码的有效性，返回boolean值
        captcha.verify("1234");
    }
}
