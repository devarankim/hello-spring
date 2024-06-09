package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X

        /* 1)  첫번째 방식 */
        Optional<Member> result = memberRepository.findByName(member.getName()); //ctrl+alt+v : return해준다
        //result.orElseGet(); //이런 메소드도 많이 쓴다. .get()은 권장하지 않는다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        /* 2) 2번째 방식 */
        //위처럼 optional을 바로 쓰면 겉으로 보였을 때 예쁘지 않음.(Optional<Member> result)
        //어차피 result가 return(optional) 되었기 때문에 아래와 같은 형식으로 코드를 정리해서 사용할 수도 있다.
        //이렇게 로직이 나와는 경우는 메소드로 가는게 좋다.
        /*  memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
         */
        validateDuplicateMember(member); //extract 하는 단축키 : ctrl+alt+m
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
