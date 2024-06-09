package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceApp { //보통 AOP는 컴포넌트 스캔보다는 BEAN에 등록하여 쓰는 것을 더 선호한다.

    @Around("execution(* hello.hellospring..*(..))") //패키지 위에 다 적용. 패키지가 아닌 클래스도 적을 수 있음. 보통은 패키지 레벨로 함
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
    //aop는 proxy(가짜클래스)를 실행해서 확인 후에 진짜 클래스를 호출함
}
