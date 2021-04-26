package com.example.demo.repository;

import com.example.demo.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
//  jpa는 entitymanager를 통해서 데이터 저장, 갱신을 한다.
    private final EntityManager em ;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = em.createQuery("select m from Member m where m.id=:id")
                .setParameter("id",id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name=:name")
                                .setParameter("name",name)
                                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m",Member.class).getResultList();
        return result;
    }

    @Override
    public void clearStore() {

    }
}
