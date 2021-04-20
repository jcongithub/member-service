package com.example.memberservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memberservice.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
