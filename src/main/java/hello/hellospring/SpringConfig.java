package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정 파일만들어서 @Configuration으로 Bean등록 하기

//@Configuration : 설정파일을 만들기 위한 어노테이션 또는 Bean을 등록하기 위한 어노테이션
//Bean을 등록할 떄 싱글톤이 되도록 보장해준다.
//@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환
//스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환
@Configuration
public class SpringConfig {

    @Bean//스프링 빈으로 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
