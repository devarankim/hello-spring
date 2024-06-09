package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepsitory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //자동으로 롤백을 해준다. 테스트를 반복해서 실행해도 된다. 단 테스트 케이스 일때에만!
class MemberServiceIntegrationTest {

    //테스트할 때에는 편한 방법으로 하면 된다. 필드 주입은 많이 지양하는 방법이지만 테스트니까.. 벗 회바회 부바부 사바사
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository; //

    @Test
    void 회원가입() { //test는 과감하게 한글로 바꿔도 됨
        //given
        Member member = new Member();
        member.setName("spring"); //

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //try catch는 요즘은 많이 애매함
        /*try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        }*/

        //then
    }
}