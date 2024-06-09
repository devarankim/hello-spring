package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceApp;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepsitory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     */
    //private EntityManager em; //jpa

    //spring jpa
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    //spring data jpa를 사용하면 아래 메소드는 주석처리 해도 됨, 갈아끼울 필요가 없음
    @Bean
    public MemberRepository memberRepository() {
        //return new JdbcRepository();
        return new MemoryMemberRepsitory(); //다형성이 좋다! 인터페이스를 두고 구현체들끼리 지유자재로 변경 가능
        //return new
    }

    @Bean
    public TimeTraceApp timeTraceApp() {
        return new TimeTraceApp();
    }
}
