package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {

    //EntityManager : Entity를 관리하는 클래스, 내부적으로 Datasource를 갖고있어 DB통신을 처리해줌
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist() : 저장 후 리턴값 없음
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //JPQL : JPQL은 엔티티 객체를 조회하는 객체지향 쿼리
        //createQuery() : JPQL로 Query를 생성시 createQuery() 메서드를 이용
        //createNamedQuery() :  이름을 부여해서 호출, JPQL 여러곳에서 사용 가능
        //createNativeQuery() : JPQL로 구현 못할 때 네이티브 SQL쿼리로 작성 가능

        //List로 가져오거나 PK로 찾는것이 아닌 것들은 JPQL사용 해야됨
        List<Member> result = em.createQuery("select m from Member m where m.name = :name ", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
