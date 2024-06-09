package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JapMemberRepository implements MemberRepository {

    private final EntityManager em; //jap는 entitymanager로 모든 동작을 다 한다.

    public JapMemberRepository(EntityManager em) { //jap를 사용하려면 entitymanager를 주입받아야 한다.
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) //jpql
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //객체 자체를 조회함 그래서 m 으로 한다
                .getResultList();
    }
}
