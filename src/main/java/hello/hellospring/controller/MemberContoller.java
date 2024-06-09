package hello.hellospring.controller;

//컨트롤러는 서비스를 의존한다. 서비스를 통하여 조회 삭제 등등을 할 수 있기 때문.
//다양한 컨트롤러에서 서비스를 의존할 수 있기 때문에 하나만 생성해서 같이 공통으로 사용하면 된다.
//그게 싱글톤이며 스프링 컨테이너에 서비스클래스를 하나만 등록한다.

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 어노테이션을 붙혀서 빈을 등록한 방법 : 컴포너트 스캔 . 그리고 스프링이 자동으로 DI해줌. 단 해당 어노테이션가 정의된 클래스에 @Component 어노테이션이 있어야 가능함
public class MemberContoller {
    private final MemberService memberService;

    /* 생성자주입 */
    @Autowired //연결해줌. controller에 필요한 서비스를 가져다가 스프링이 넣어줌 --> 의존관계 주입 DI
    public MemberContoller(MemberService memberService) {
        this.memberService = memberService;
    }

    /* 필드주입* /
    //@Autowired private final MemberService; //사용 지양. 중간에 내가 바꿔지치 할 방법이 없음

    /* setter주입 */
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    } // 단점 : 우선 메소드가 PUBLIC으로 열려있어야 함. 즉 여기저기서 호출 할 수 있게 열려있는 것이다.



    @GetMapping(value = "/members/new") //조회 get
    public String creatForm() {
        return "members/creatMemberForm";
    }

    @PostMapping(value = "/members/new") //등록 post
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
