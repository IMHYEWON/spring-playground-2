package com.hyewon.springplayground2;

import com.hyewon.springplayground2.domain.member.Grade;
import com.hyewon.springplayground2.domain.member.Member;
import com.hyewon.springplayground2.domain.member.service.MemberService;
import com.hyewon.springplayground2.domain.member.service.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member newMember = new Member(1L, "hyewon", Grade.VIP);
        memberService.join(newMember);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember == findMember: " + (newMember == findMember));
        System.out.println("findMember : " + findMember.getName());
        System.out.println("newMember : " + newMember.getName());
    }
}
