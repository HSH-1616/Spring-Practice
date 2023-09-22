package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//시간을 측정하는 로직은 공통 관심 사항이고 비즈니스의 로직과 섞어 사용하면 유지보수가 어렵다
//Aop를 사용하여 시간을 측정하는 로직을 별도의 공통 로직으로 만들면 핵심 관심 사항을 깔끔하게 유지할 수 있다.
//변경이 필요하면 이 로직만 변경하면 되고 원하는 적용 대상을 선택할 수 있다.

@Aspect //Aop를 사용하기 위해 Aspect클래스임을 정의
@Component
public class TimeTraceAop {

    //@Before : 패턴에 해당하는 메소드를 실행전에 동작
    //@After : 패턴에 해당하는 메소드를 실행후에 동작
    //@Around : 패턴에 해당하는 메소드를 실행전,후에 동작
    @Around("execution(* hello.hellospring..*(..))")//패턴에 해당하는 메소드를 실행전,후에 동작
    //JoinPoint : 어플리케이션 실행 흐름에서의 특정 포인트(AOP를 적용할 수 있는 지점)를 의미
    //ProceedingJoinPoint : JoinPoint를 상속받은 클래스, @Around에서는 proceed() 메소드가 필요하기 때문에 ProceedingJoinPoint사용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        //메소드 실행 시작 시간
        long start = System.currentTimeMillis();

        System.out.println("START : "+joinPoint.toString());

        try {
            //proceed : 다음 어드바이스나 타켓을 호출
            return joinPoint.proceed();
        }finally {
            //메소드 실행 끝난 시간
            long finsih = System.currentTimeMillis();

            //끝난 시간 - 시작 시간
            long timeMs = finsih - start;
            System.out.println("END : " + joinPoint.toString() + " "+timeMs+"ms");
        }
    }

}
