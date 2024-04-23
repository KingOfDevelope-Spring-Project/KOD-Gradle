package com.koreait.kod.biz.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocialMemberDAO {

    public MemberDTO selectOne(Map<String,String> map);
    public boolean insert(Map<String,String> map);
}