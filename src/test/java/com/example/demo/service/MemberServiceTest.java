package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.assertj.core.api.Assertions.*;
class MemberServiceTest {
    MemoryMemberRepository repository;
    MemberService memberService;
    @BeforeEach
    public void beforeEach(){

        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    void join() {
//        given
        Member member = new Member();
        member.setName("spring");
//        when
        Long saveId= memberService.join(member);
//        then
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원(){
        Member member = new Member();
        member.setName("spring");

        Member member1 = new Member();
        member1.setName("spring");

        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        org.assertj.core.api.Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("이미 회원입니다");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}