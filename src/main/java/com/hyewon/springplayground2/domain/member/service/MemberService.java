package com.hyewon.springplayground2.domain.member.service;

import com.hyewon.springplayground2.domain.member.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
    void upgradeGrade(Member member);
    void downgradeGrade(Member member);
}
