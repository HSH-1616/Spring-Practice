package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {//인터페이스가 인터페이스를 받을 때는 extends 사용
    //JpaRepository를 받고있으면 SpringDataJpa가 구현체를 자동으로 만들어 줘서 Bean으로 등록해줌

    //JpaRepository에서 기본적으로 findAll(), save()등 공통적인 crud메소드들을 제공하기 떄문에 간단한 기능은 인터페이스만 작성해도 개발이 가능하다.
    //하지만 모든 비즈니스에서 공통화 할수 없기 떄문에 복잡한 기능이나 필요한 기능은 따로 추가할수 있다.
    //복잡한 동적쿼리는 Querydsl이라는 라이브러리를 사용
    @Override
    Optional<Member> findByName(String name);
}
