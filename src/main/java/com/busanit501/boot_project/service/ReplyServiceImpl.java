package com.busanit501.boot_project.service;

import com.busanit501.boot_project.domain.Board;
import com.busanit501.boot_project.domain.Reply;
import com.busanit501.boot_project.dto.ReplyDTO;
import com.busanit501.boot_project.repository.BoardRepository;
import com.busanit501.boot_project.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    // 디비에 작업할 기능 포함, 외주주기,
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        log.info("ReplyServiceImpl 에서, 화면으로 부터 전달 받은 데이터 확인 replyDTO: " + replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        // 화면으로 부터 전달받은 데이터 bno 가 있고, 변환 되는 과정에서 해당 객체를 넣는 부분이 빠졌음.
        Optional<Board> result = boardRepository.findById(replyDTO.getBno());
        Board board = result.orElseThrow();
        reply.changeBoard(board);
        log.info("ReplyServiceImpl 에서, 화면으로 부터 전달 받은 데이터 확인2 reply: " + reply);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        // 디비에서 조회 후, -> 옵션널 받고 -> 다시 엔티티 변환,
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        log.info("ReplyServiceImpl 에서, read ,데이터 확인 reply: " + reply);
        // 한줄로 표기하기.
//        Reply reply = replyRepository.findById(rno).orElseThrow();
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        replyDTO.setBno(reply.getBoard().getBno());
        log.info("ReplyServiceImpl 에서, read ,데이터 확인2 replyDTO: " + replyDTO);
        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        log.info("ReplyServiceImpl 에서, modify ,데이터 확인 replyDTO: " + replyDTO);
        // 기존 댓글을 불러와서,
        Reply reply = replyRepository.findById(replyDTO.getRno()).orElseThrow();
        log.info("ReplyServiceImpl 에서, modify ,데이터 확인2 reply: " + reply);
        // 교체할 데이터로 교체 작업 후,
        reply.changeReplyText(replyDTO.getReplyText());

        // 교체 후, 데이터 확인 한번 더
        log.info("ReplyServiceImpl 에서, modify ,데이터 확인3 reply: " + reply);

        // 다시 저장.
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
