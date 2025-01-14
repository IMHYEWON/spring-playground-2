package com.hyewon.springplayground2.domain.member.service;

import com.hyewon.springplayground2.domain.member.repository.MemberRepository;
import com.hyewon.springplayground2.domain.member.repository.MemoryMemberRepository;
import com.hyewon.springplayground2.domain.member.Member;

public class MemberServiceImpl implements MemberService {

    // 구현체를 의존하고 있음, DIP원칙 위반
    // DIP Principe : 추상화에 의존해야지 구체화에 의존하면 안된다
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        // 회원 가입
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        // 회원 조회
        return memberRepository.findById(memberId);
    }

    @Override
    public void upgradeGrade(Member member) {
        // 등급 업그레이드
    }

    @Override
    public void downgradeGrade(Member member) {
        // 등급 다운그레이드
    }
}
