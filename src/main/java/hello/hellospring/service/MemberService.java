package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {


    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member) {

        //메소드 실행 시작 시간
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); //중복 검사 검증 메소드
            memberRepository.save(member);
            return member.getId();
        } finally {//메소드가 끝나면 시간 측정

            //메소드 실행 끝난 시간
            long finish = System.currentTimeMillis();

            //끝난 시간 - 시작 시간
            long timeMs = finish - start;

            //System.out.println("join = " + timeMs +"ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 회원 중복 검사
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        //ifPresent() : Optional로 감싼 객체가 값을 가지고 있을 경우 실행
        //isPresent() : Optional로 감싼 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴

        //Optional로 감싼 객체가 null일 경우 값을 넘겨줘야 할 때
        //orElse() : 값이 있던 null이던  무조건 실행
        //orElseGet() : null일 경우에만 실행
    }

    //전체 회원 조회
    public List<Member> findMebers() {
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            //System.out.println("findMembers " + timeMs + "ms");
        }
    }

    //회원 1명 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

