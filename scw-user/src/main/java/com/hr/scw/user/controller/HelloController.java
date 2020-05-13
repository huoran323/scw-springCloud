package com.hr.scw.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="测试Swagger工具helloWorld")
@RestController
public class HelloController {

	@ApiImplicitParams(value={
			@ApiImplicitParam(value="姓名",name="name",required=true),
			@ApiImplicitParam(value="年龄",name="age",required=true)
	})
	@ApiOperation(value="演示接口调用")
	@GetMapping("/hello")
	public String hello(String name, Integer age) {
		return "OK: " + name + " - " + age;
	}
}
