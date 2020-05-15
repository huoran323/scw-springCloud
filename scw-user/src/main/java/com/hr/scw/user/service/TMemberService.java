package com.hr.scw.user.service;

import com.hr.scw.user.vo.req.UserRegistVo;
import com.hr.scw.user.vo.resp.UserRespVo;

public interface TMemberService {

	int saveTMember(UserRegistVo vo);

	UserRespVo getUserByLogin(String loginacct, String password);

}
