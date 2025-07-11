package com.busanit501.boot_project.controller;

import com.busanit501.boot_project.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Long>> register(@RequestBody ReplyDTO replyDTO) {
        log.info("ReplyController에서 작업중, 댓글 작성작업");
        log.info("replyDTO : " + replyDTO);
        // 화면이 없어서, 하드코딩으로 더미 데이터 만들기.
        Map<String,Long> resultMap = Map.of("rno",100L);
        return ResponseEntity.ok(resultMap);
    }
}
