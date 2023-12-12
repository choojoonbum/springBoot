package org.choo.springboot.repository;

import org.choo.springboot.entity.Member2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class Member2RepositoryTests {
    @Autowired
    private Member2Repository memberRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member2 member = Member2.builder().email("r" + i + "@choo.org").pw("1111").nickname("reviewer" + i).build();
            memberRepository.save(member);
        });
    }

    @Test
    @Commit
    @Transactional
    public void testDeleteMember() {
        Long mid = 2L;
        Member2 member = Member2.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
