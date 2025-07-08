package com.busanit501.boot_project.repository;

import com.busanit501.boot_project.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    //JpaRepository 를 이용해서, 기본 crud 확인.
    // sql 를 따로 몰라도, 자바의 메서드만 호출해서, sql 전달하기.
    // 1 insert 확인,
    @Test
    public void testInsert() {
        // 더미 데이터, 병렬 처리로, 100개 정도, 하드 코딩하기.
        IntStream.rangeClosed(1,100).forEach(i -> {
            // 엔티티 클래스 , 임시 객체를 생성을 반복문에 맞춰서 100개 생성.
            Board board = Board.builder()
                    .title("title____" + i)
                    .content("content____" + i)
                    .writer("user_____"+(i%10))
                    .build();
            // 디비에 반영하기. save, insert sql 문장과 결과 동일.
            //====================== JpaRepository에서 확인 하는 부분은 여기==================================
            Board result = boardRepository.save(board);
            //====================== JpaRepository에서 확인 하는 부분은 여기==================================
            log.info("bno : " + result.getBno());
        });
    }// 1 insert 확인,

    @Test
    public void testSelect() {
        Long tno = 100L;
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        Optional<Board> result = boardRepository.findById(tno);
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        Board board = result.orElseThrow();
        log.info("bno : " + board);
    }

}
