package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//단위 테스트 : 순수한 자바 코드만 테스트
class MemberServiceTest {

    //new를 이용해서 새로운 서로 다른 인스턴스를 생성 하는 것은 좋지않음
    //실제로 사용할 때와 테스트 할때 인스턴스를 동일하게 해주자
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach//하나의 메소드가 시작하기 전에  실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {//테스트 할떄는 한글로 바꿔도 상관없음

        //given : 테스트의 상태를 설정  [준비]
        Member member = new Member();
        member.setName("spring");

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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        //중복이름을 넣어줬기 떄문에 예외처리가 실행되야함
        //예외가 발생하면 test성공
        memberService.join(member1);


        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

       /* memberService.join(member1);
        try{
            memberService.join(member1);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }*/

        //then
    }


    @Test
    void findMebers() {
    }

    @Test
    void findOne() {
    }
}