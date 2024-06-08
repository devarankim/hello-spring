package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); //NULL을 그대로 반환하는 대신에 Opiotnal로 감까서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
