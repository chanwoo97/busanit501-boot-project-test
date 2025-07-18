package com.busanit501.boot_project.service;

import com.busanit501.boot_project.domain.Board;
import com.busanit501.boot_project.dto.*;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    //    기존 , 1) 페이징 2) 검색
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // 기존 , 1) 페이징 2) 검색 3) 댓글 갯수 , 버전으로 목록 출력.
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    // 기존 , 1) 페이징 2) 검색 3) 댓글 갯수 , 버전으로 목록 출력. 4) 첨부 이미지들
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    // 엔티티 <-> DTO 변환, 기본 메서드로 정의 해두기.
    //1) dto -> board 변환 하기.
    default Board dtoToEntity(BoardDTO boardDTO) {
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        // 이미지 첨부 작업.
        if(boardDTO.getFileNames() != null) {
            boardDTO.getFileNames().forEach(fileName -> {
                // 기존 파일명에 혹시나, "_" 같이 있는 이름은 오류가 나는게 흠.
                // 썸네일 : s_uuid_이미지파일명.jpg
                // 파일 : uuid_파일명.확장자
                String[] arr= fileName.split("_");
                board.addImage(arr[0], arr[1]);

            }); // end forEach
        } // end if
        return board;
    }



}
