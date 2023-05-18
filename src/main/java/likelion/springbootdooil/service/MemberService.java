package likelion.springbootdooil.service;

import likelion.springbootdooil.domain.Member;

import java.util.List;


public interface MemberService {
    public void  save(Member member);
    public Member findById(Long id);
    public List<Member> findAll();


}
