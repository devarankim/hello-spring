package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

//package hello.hellospring 하위에 있는 클래스들을 다 찾아서 스프링이 bean으로 등록한다.(컴포너트 스캔)
//예시가 hello.hellopsring거지 main메소드가 있는 클래스파일에 pacakge를 확인해야 한다.
