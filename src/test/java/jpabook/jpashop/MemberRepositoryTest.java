package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // entity manager을 통한 모든 데이터 변경은 항상 transaction 안에서 이루어져야함
    public void testMember() throws Exception{
        //given
        TestMember testMember = new TestMember();
        testMember.setUsername("memberA");
        //when
        Long savedId = memberRepository.save(testMember);
        TestMember findTestMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findTestMember.getId()).isEqualTo(testMember.getId());
        Assertions.assertThat(findTestMember.getUsername()).isEqualTo(testMember.getUsername());
        Assertions.assertThat(findTestMember).isEqualTo(testMember);
    }
}