package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//transactional를 달면 테스트케이스가 끝난 경우 role back을 해준다
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberRepository repository;
    @Autowired MemberService memberService;

    @Test
    void join() {
//        given
        Member member = new Member();
        member.setName("spring1");
//        when
        Long saveId= memberService.join(member);
//        then
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원(){
        Member member = new Member();
        member.setName("spring1");

        Member member1 = new Member();
        member1.setName("spring1");

        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        org.assertj.core.api.Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("이미 회원입니다");
    }

}