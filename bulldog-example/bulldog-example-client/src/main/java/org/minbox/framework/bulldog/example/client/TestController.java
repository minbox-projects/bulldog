package org.minbox.framework.bulldog.example.client;

import org.minbox.framework.bulldog.client.global.GlobalLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author 恒宇少年
 */
@RestController
public class TestController {

    @Autowired
    private GlobalLogging globalLogging;

    @GetMapping(value = "/test")
    public String test() {
        globalLogging.info("这是第一条日志内容");
        callMethod2();
        callMethod3();
        return "测试日志接口";
    }

    private void callMethod2() {
        globalLogging.debug("这是第二条");
    }

    private void callMethod3() {
        try {
            int a = 3 / 0;
        } catch (Exception e) {
            globalLogging.error(e.getMessage(), e);
        }
    }
}
