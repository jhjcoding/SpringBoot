package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memoryMemberRepository;

    public MemberService(MemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }


    public Long join (Member member){

        duplicate(member);
        memoryMemberRepository.save(member);
        return member.getId();
    }

    private void duplicate (Member member){

        memoryMemberRepository.findByName(member.getName()).ifPresent(m->{throw new IllegalStateException("이미 존재하는 회원입니당.");});
    }

    public List<Member> findMembers (){

        return memoryMemberRepository.findAll();
    }

    public Optional<Member> findOne (Long memberId){

        return memoryMemberRepository.findById(memberId);
    }

}
