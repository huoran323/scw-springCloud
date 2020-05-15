package com.hr.scw.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hr.scw.user.bean.TMember;

public interface TMemberMapper {

	int insertSelective(TMember member);

	List<TMember> selectUser(@Param("loginacct")String loginacct, @Param("userpswd")String userpswd);

}
