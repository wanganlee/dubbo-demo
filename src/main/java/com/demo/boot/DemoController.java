package com.demo.boot;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * @author developer.wang
 * @date 2020/3/3
 */
@RestController
@RequestMapping("/demo/")
public class DemoController {

    /**
     * consumers:指定接收哪种类型的参数；produces:指定响应哪种类型的参数
     */
    @PostMapping(value = "index", consumes = {"application/json;charset=UTF-8"}, produces = "application/xml;charset=UTF-8")
    public Map<String, String> index(Map<String, Object> param) {
        Objects.requireNonNull(param, "param must not be null");
        return Maps.newHashMap();
    }
}
