package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//Transactional에 (readOnly = true) 를 넣으면 단순 조회에서는 성능이 조금 나아질수 있다
@RequiredArgsConstructor //모든 argument를 전부받는 생성자 -> 가장 좋은 의존성 주입방법이다.
public class MemberService {


    private final MemberRepository memberRepository;

    /**
     * 의존성 주입 3가지 방법
     * 1. setter 주입 -> 런타임 같을때 중간에 값을 바꿔버릴수 있다. (위험)
     * 2. 바로 주입 -> 주입하기가 까다롭다
     * 3. 생성자 주입 -> 생성시점에 주입하여 테스트 케이스 만들거나 할때 용이하다
     */



    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional //조회하는 메서드가 아니라서 readOnly를 안넣는다
    public Long join(Member member){
        validationDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validationDuplicateMember(Member member) {
        //multithread 같은 여러 이름이 동시에 회원가입될수 있는 상황을 고려하여 unique 제약조건 걸어놓는게 좋다
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);

    }
}
