package com.hr.scw.user.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户注册提交的数据VO
 * @author zhengdayong
 *
 */
@ApiModel
public class UserRegistVo {

	@ApiModelProperty("手机号")
	private String loginacct;
	
	@ApiModelProperty("密码")
	private String userpswd;
	
	@ApiModelProperty("邮箱")
	private String email;
	
	@ApiModelProperty("验证码")
	private String code;
	
	@ApiModelProperty("用户类型：0 - 个人，1 - 企业")
	private String usertype;

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getLoginacct() {
		return loginacct;
	}

	public void setLoginacct(String loginacct) {
		this.loginacct = loginacct;
	}

	public String getUserpswd() {
		return userpswd;
	}

	public void setUserpswd(String userpswd) {
		this.userpswd = userpswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
