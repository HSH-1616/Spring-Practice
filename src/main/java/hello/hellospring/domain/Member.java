package hello.hellospring.domain;

import javax.persistence.*;

@Entity //데이터 모델링에서 사용되는 객체, Jpa를 사용해서 테이블과 매핑할 클래스에 붙임
public class Member {

    @Id //테이블 PK와 객체의 필드를 매핑
    //@GenaratedValue : 기본 키를 자동 생성해주는 어노테이션

    //starategy : 자동 생성 전략 4가지가 있음

    //DENTITY : 기본 키 생성을 데이터베이스에 위임
    //SEQUENCE : DB시퀀스를 매핑해서 기본 키를 생성
    //TABLE : 키 생성 전용 테이블을 만들어서 사용
    //AUTO : 데이터베이스에 따라 기본키를 자동으로 생성

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") //테이블 컬럼과 매핑 이름이 같으면 생략가능, 기본값으로 nullable =true임 nullable = false로 설정 가능
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
