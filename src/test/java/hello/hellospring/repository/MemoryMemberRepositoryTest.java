package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Junit : 자바 애플리케이션에 대한 단위 테스트를 쉽게 해 주는 테스트용 프레임워크
//AssertJ : 자바 JUnit의 테스트코드에 사용되어 테스트코드의 가독성과 편의성을 높여 주는 라이브러리, assertThat()으로 사용
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //하나의 메소드가 끝날때 마다 실행
    public void afterEach() {
        //메모리 비워주기
        repository.clearStore();
    }

    @Test //테스트 대상 메소드 지정
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //asserThat() : 두 값을 비교하는 메소드, 상황에 맞게 다양한 matcher를 붙여서 구현가능
        //isEqualTo() : 실제값이 기대값과 같은지 비교
        assertThat(result).isEqualTo(member);//assertThat(실제값)isEquaslTo(기대값)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
