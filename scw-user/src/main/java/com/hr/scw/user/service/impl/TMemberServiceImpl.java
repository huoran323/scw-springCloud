package com.hr.scw.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.scw.user.bean.TMember;
import com.hr.scw.user.mapper.TMemberMapper;
import com.hr.scw.user.service.TMemberService;
import com.hr.scw.user.vo.req.UserRegistVo;

@Service
public class TMemberServiceImpl implements TMemberService {

	@Autowired
	TMemberMapper memberMapper;

	@Override
	public int saveTMember(UserRegistVo vo) {
		
		//将vo属性对拷到do对象中
		TMember member = new TMember();
		BeanUtils.copyProperties(vo, member);
		member.setUsername(vo.getLoginacct());
		
		int i = memberMapper.insertSelective(member);
		return i;
	}
}
