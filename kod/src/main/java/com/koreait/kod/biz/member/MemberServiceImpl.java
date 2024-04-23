package com.koreait.kod.biz.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("memberService")
public class MemberServiceImpl implements MemberService{
   @Autowired 
   MemberDAO memberDAO;
   
   @Autowired
   IMemberDAO imemberDAO;

   Map<String, String> map = new HashMap<String, String>(); 


   @Override
   public MemberDTO selectOne(MemberDTO memberDTO) {
      return memberDAO.selectOne(memberDTO);
   }

   @Override
   public boolean insert(MemberDTO memberDTO) {
      return memberDAO.insert(memberDTO); 
   } 


   @Override
   public boolean delete(MemberDTO memberDTO) {
      return memberDAO.delete(memberDTO);
   }


}