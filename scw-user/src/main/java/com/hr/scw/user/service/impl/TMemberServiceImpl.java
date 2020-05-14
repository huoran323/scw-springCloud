package com.hr.scw.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hr.scw.enums.UserExceptionEnum;
import com.hr.scw.user.bean.TMember;
import com.hr.scw.user.exp.UserException;
import com.hr.scw.user.mapper.TMemberMapper;
import com.hr.scw.user.service.TMemberService;
import com.hr.scw.user.vo.req.UserRegistVo;

@Service
@Transactional(readOnly = true)
public class TMemberServiceImpl implements TMemberService {

	@Autowired
	TMemberMapper memberMapper;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	@Override
	public int saveTMember(UserRegistVo vo) {

		try {
			// 将vo属性对拷到do对象中
			TMember member = new TMember();
			BeanUtils.copyProperties(vo, member);
			member.setUsername(vo.getLoginacct());

			int i = memberMapper.insertSelective(member);
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException(UserExceptionEnum.USER_SAVE_ERROR);
		}
	}
}
