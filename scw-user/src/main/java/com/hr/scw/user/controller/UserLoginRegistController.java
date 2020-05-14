package com.hr.scw.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hr.scw.user.service.TMemberService;
import com.hr.scw.user.vo.req.UserRegistVo;
import com.hr.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@Controller
public class UserLoginRegistController {
	
	@Autowired
	TMemberService memberService;

	@ResponseBody
	@ApiOperation(value="用户注册")
	@PostMapping("/register")
	public AppResponse<Object> register(UserRegistVo vo) {
		
		String loginacct = vo.getLoginacct();
		System.out.println("用户名：  " + loginacct);
		if (!StringUtils.isEmpty(loginacct)) {
			
			int i = memberService.saveTMember(vo);
			if (i == 1) {
				return AppResponse.ok("ok");
			} else {
				return AppResponse.fail(null);
			}
			
		} else {
			AppResponse resp = AppResponse.fail(null);
			resp.setMsg("用户名称不能为空！");
			return resp;
		}
		
		
	}
}
