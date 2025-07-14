package com.busanit501.boot_project.repository;

import com.busanit501.boot_project.domain.Board;
import com.busanit501.boot_project.domain.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        // 댓글 작성 테스트,
        // 준비물 1) 실제 부모 게시글 존재,
        Long bno = 109L;
        
        //부모 게시글 더미 데이터,
        Board board = Board.builder()
                .bno(bno).build();
        
        // 댓글의 더미 데이터 
        Reply reply = Reply.builder()
                // 부모 게시글 객체는 반드시 필요함. 
                .board(board)
                .replyText("샘플 게시글 내용")
                .replyer("샘플 댓글 작성자")
                .build();

        replyRepository.save(reply);
    }

}
