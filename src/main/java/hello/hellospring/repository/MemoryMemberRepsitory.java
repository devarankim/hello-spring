package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepsitory implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //동시성 문제가 있을 수 있어서 concurrentHashMap을 사용해야 한다.(실무)
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //값이 null이여도 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) { //결과는 optional로 반환
        return store.values().stream() //for문
                .filter(member -> member.getName().equals(name))
                .findAny(); //findAny는 하나라도 찾는 것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }
}
