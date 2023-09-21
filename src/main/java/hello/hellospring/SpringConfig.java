package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정 파일만들어서 @Configuration으로 Bean등록 하기

//@Configuration : 설정파일을 만들기 위한 어노테이션 또는 Bean을 등록하기 위한 어노테이션
//Bean을 등록할 떄 싱글톤이 되도록 보장해준다.
//@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환
//스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환
@Configuration
public class SpringConfig {

    //DataSource : 데이터베이스 커넥션을 획득할 때 사용하는 객체
    //스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어둔다. 그래서 DI를 받을 수 있다.
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //EntityManager : Entity를 관리하는 클래스, 내부적으로 Datasource를 갖고있어 DB통신을 처리해줌
//    private EntityManager em;

    //Jpa를 사용하고 위해 의존성 주입
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);

//    }

}
