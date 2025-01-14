package com.hyewon.springplayground2.domain.discount;

import com.hyewon.springplayground2.domain.member.Member;

public interface DiscountPolicy {

    /**
     * 할인 정책
     * @param member
     * @param price
     * @return
     */
    int discount(Member member, int price);
}
