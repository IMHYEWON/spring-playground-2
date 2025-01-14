package com.hyewon.springplayground2.domain.member.repository;

import com.hyewon.springplayground2.domain.member.Member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long memberId);
}
