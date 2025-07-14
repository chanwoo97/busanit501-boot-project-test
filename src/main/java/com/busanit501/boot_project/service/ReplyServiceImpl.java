package com.busanit501.boot_project.service;

import com.busanit501.boot_project.domain.Reply;
import com.busanit501.boot_project.dto.ReplyDTO;
import com.busanit501.boot_project.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    // 디비에 작업할 기능 포함, 외주주기,
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        log.info("ReplyServiceImpl 에서, 화면으로 부터 전달 받은 데이터 확인 replyDTO: " + replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        log.info("ReplyServiceImpl 에서, 화면으로 부터 전달 받은 데이터 확인2 reply: " + reply);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }
}
