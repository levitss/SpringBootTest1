package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    회원가입
     */
    public long join(Member member) {
        validateDuplicatedMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원");
        });
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long mem_id) {
        return memberRepository.findbyId(mem_id);
    }


}
