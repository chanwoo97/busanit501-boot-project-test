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
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }
}
