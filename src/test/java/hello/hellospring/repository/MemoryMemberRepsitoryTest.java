package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//TDD : 테스트주도개발 / 1)테스트를 먼저 하고 2)구현 클래스를 만들고 3)돌려보는 것
public class MemoryMemberRepsitoryTest {
    MemoryMemberRepsitory memberRepository = new MemoryMemberRepsitory();

    @AfterEach
    public void afterEach(){ //콜백메소드. 메소드 실행이 끝날때마다 실행됨.
        memberRepository.clearStore();

    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get(); //바로 꺼내면 좋지 않지만 테스트코드에서는 괜찮다. 옵셔널에서 값 꺼낼 때에는 get() 사용
        //Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result); //요즘은 이거 많이 사용함 static import해줘야 함.

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}

