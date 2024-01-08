package com.example.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private final static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);

        log.info("save member={}",member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

    public void clearStore() {
        store.clear();
    }

}
