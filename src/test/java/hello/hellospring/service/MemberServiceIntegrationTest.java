package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//통합 테스트 : Spring 컨테이너와 DB까지 모두 테스트
@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행
@Transactional  //테스트 케이스에 사용시 테스트 완료후 롤백, DB에 남지 않음
class MemberServiceIntegrationTest {

    //테스트에서는 다른데에서 사용하지 않기 때문에 제일 간편한 필드주입 많이 사용
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //@Commit //DB에 저장
    void 회원가입() {

        //given : 테스트의 상태를 설정  [준비]
        Member member = new Member();
        member.setName("spring4");

        //when : 테스트 하고자 하는 행동  [실행]
        Long saveId = memberService.join(member);

        //then : 테스트가 완료됐을 때 보장해야 하는 결과, 예상되는 변화 설명 [검증]
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring4");

        Member member2 = new Member();
        member2.setName("spring4");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}