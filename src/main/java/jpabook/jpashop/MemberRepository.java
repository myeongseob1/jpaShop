package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //커맨드와 쿼리를 분리하자
    public Long save(TestMember testMember){
        em.persist(testMember);
        return testMember.getId();
    }

    public TestMember find(Long id){
        return em.find(TestMember.class, id);
    }
}
