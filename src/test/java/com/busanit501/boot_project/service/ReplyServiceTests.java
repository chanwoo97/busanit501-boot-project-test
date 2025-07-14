package com.busanit501.boot_project.service;

import com.busanit501.boot_project.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister() {
        // 실제 디비에, 게시글이 존재해야함.
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("샘플 서비스 테스트, 댓글 작성3")
                .replyer("이상용")
                .bno(108L)
                .build();

        log.info("작성한 댓글 번호 : "+replyService.register(replyDTO));
    }
}
