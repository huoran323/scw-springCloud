package com.hr.scw.user.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.hr.scw.user.vo.resp.UserRespVo;

@Service
@Transactional(readOnly = true)
public class TMemberServiceImpl implements TMemberService {

	@Autowired
	TMemberMapper memberMapper;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	@Override
	public int saveTMember(UserRegistVo vo) {

		try {
			// 将vo属性对拷到do对象中
			TMember member = new TMember();
			BeanUtils.copyProperties(vo, member);
			member.setUsername(vo.getLoginacct());
			
//			String userpswd = vo.getUserpswd();
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			member.setUserpswd(encoder.encode(userpswd));

			int i = memberMapper.insertSelective(member);
			return i;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException(UserExceptionEnum.USER_SAVE_ERROR);
		}
	}

	@Override
	public UserRespVo getUserByLogin(String loginacct, String userpswd) {
		
		UserRespVo vo = new UserRespVo();
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		List<TMember> list = memberMapper.selectUser(loginacct, userpswd);
		
		if (list==null || list.size() == 0) {
			throw new UserException(UserExceptionEnum.LOGINACCT_UNEXIST);
		}
		
		TMember member = list.get(0);
//		if (!encoder.matches(userpswd, member.getUserpswd())) { //密码比较
//			throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
//		}
		
		if (userpswd.equals(member.getUserpswd())) { //密码比较
			throw new UserException(UserExceptionEnum.USER_PASSWORD_ERROR);
		}
		
		BeanUtils.copyProperties(member, vo);
		
		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
		vo.setAccessToken(accessToken);
		
		//将token存储到redis中
		stringRedisTemplate.opsForValue().set(accessToken, member.getId().toString(),30,TimeUnit.MINUTES);
		
		return vo;
	}
}
