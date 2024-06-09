package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepsitory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepsitory repository; //
    // MemoryMemberRepsitory repository = new MemoryRepository(); 은 인스턴스를 새로 만들었기 때문에 service에 선언되어 있는 new와 전혀 다른 객체다.
    // 현재는 map이 static이라서 상관없지만 아니면 전혀 다른 db가 될 수 있음.

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepsitory();
        memberService = new MemberService(repository); //내가 직접 new 하지 않음. memberserivce에서 넣어줌. DI
    }

    //test라도 로직 실행할 수록 계속 메모리에 쌓이고 있다. cleart해줘야 함
    @AfterEach
    public void afterEach(){ //콜백메소드. 메소드 실행이 끝날때마다 실행됨.
        repository.clearStore();

    }

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

        Member member2 =  new Member();
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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}