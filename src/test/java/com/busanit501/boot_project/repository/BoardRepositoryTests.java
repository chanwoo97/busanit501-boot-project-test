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
        IntStream.rangeClosed(1, 100).forEach(i -> {
            // 엔티티 클래스 , 임시 객체를 생성을 반복문에 맞춰서 100개 생성.
            Board board = Board.builder()
                    .title("title____" + i)
                    .content("content____" + i)
                    .writer("user_____" + (i % 10))
                    .build();
            // 디비에 반영하기. save, insert sql 문장과 결과 동일.
            //====================== JpaRepository에서 확인 하는 부분은 여기==================================
            Board result = boardRepository.save(board);
            //====================== JpaRepository에서 확인 하는 부분은 여기==================================
            log.info("bno : " + result.getBno());
        });
    }// 1 insert 확인,

    // 2 , select
    @Test
    public void testSelect() {
        Long tno = 100L;
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        Optional<Board> result = boardRepository.findById(tno);
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        Board board = result.orElseThrow();
        log.info("bno : " + board);
    }
    // 2 , select

    //3. update
    @Test
    public void testUpdate() {
        Long bno = 100L;
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        //Db 로 부터 조회 된 데이터를 임시 객체에 담기
        Optional<Board> result = boardRepository.findById(bno);
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        // 패턴이니 잘 숙지, 있으면 엔티티 클래스 타입으로 받고, 없으면 예외 발생시키기
        Board board = result.orElseThrow();
        // 변경할 제목, 내용만 교체 하면 됨.
        board.changTitleContent("수정제목","수정, 오늘 점심 뭐 먹지?");
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================
        // 실제 디비에 반영.
        boardRepository.save(board);
        //====================== JpaRepository에서 확인 하는 부분은 여기==================================

    } //3. update

    //4. delete
    @Test
    public void testDelete() {
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }


}
