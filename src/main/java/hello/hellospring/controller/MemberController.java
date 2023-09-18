package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller//스프링 빈 자동 등록
public class MemberController {

    private MemberService memberService;

    //DI(Dependency Injection) : 의존성 주입 생성자, 필드, setter 3가지 방식이 있다.
    //@Autowired : 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입, 생성자가 1개만 있으면 생략 가능

    // 생성자 주입 : 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //필드 주입 : 외부에서 수정이 불가능 하고 @Autowired 어노테이션으로 의존성 주입을 남발할 수 있다.
    //@Autowired private MemberService memberService;

    //setter 주입 : public으로 구현 해야 해서 노출 되기 떄문에 중간에 바뀔 가능성이 있다.
    /*public void setMemberService(MemberService memberService){
       this.memberService = memberService;
    }*/

}
