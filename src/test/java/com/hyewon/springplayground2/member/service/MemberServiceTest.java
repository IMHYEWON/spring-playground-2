package com.hyewon.springplayground2.member.service;

import com.hyewon.springplayground2.domain.member.Grade;
import com.hyewon.springplayground2.domain.member.Member;
import com.hyewon.springplayground2.domain.member.service.MemberService;
import com.hyewon.springplayground2.domain.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "hyewon", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        assertEquals(member, findMember);
    }
}