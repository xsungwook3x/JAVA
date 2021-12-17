package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {//com+shift+t 자동생성됨

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach//시작하기 전에 넣어 주세요 라는 의미
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {//과감히 한글로 ㄲ
        //given 무언가주어지면
        Member member =new Member();
        member.setName("hello");

        //when 언제 뭐할때
        Long saveId = memberService.join(member);

        //then 그러고나서
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test//예외 테스트가 더 중요 이렇게 해줘야함
    public void 중복회원예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");


        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //cmd+option+v 하면 자동으로 앞에 인자 만들어줌

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /**try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        }catch (IllegalStateException e){
             Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}