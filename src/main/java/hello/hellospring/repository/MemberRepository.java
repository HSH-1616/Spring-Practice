package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    //Optional<> : java 8에 새로된 기능, null이 올 수 있는 값을 감싸는 Wrapper 클래스로 NPE를 회피할수 있다.
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
