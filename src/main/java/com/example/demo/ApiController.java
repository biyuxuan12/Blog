package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
@Api(description = "这个api传入一个json对象，对对象格式暂无要求")
public class ApiController {


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(
            value = "Post a json to this api",
            notes = "传一个json进来就完事了，对json没有任何具体要求",
            response = String.class,
            tags = {"这个tag会显示在ui上"})
    public String welcome(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
     return (jsonParam.toJSONString());
    }
}
