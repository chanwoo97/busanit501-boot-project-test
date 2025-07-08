package com.busanit501.boot_project.repository.search;

import com.busanit501.boot_project.domain.Board;
import com.busanit501.boot_project.domain.QBoard;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// 인터페이스이름 + Impl, 이름 규칙, 동일하게 작성,
// QuerydslRepositorySupport : 부모클래스, Querydsl 사용하기 위한 도구함.
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        // 부모 클래스에서, 엔티티 클래스 전달. 사용할 클래스 지정.
        super(Board.class);
    }

    @Override
    public Page<Board> search(Pageable pageable) {
        // 자바 문법으로만, sql 명령어 전달 하는게 목적.

        // QueryDSL ,사용법,
        // 순서1
        // Q도메인 객체 : 엔티티 클래스 Board, 동적 쿼리 작업 하기 위한
        // 편하게 만든 클래스라고 생각하면됨.
        // 기능이 향상된 버전이다.
        QBoard board = QBoard.board;
        // 순서2
        JPQLQuery<Board> query = from(board); // select .. from board 형식과 동일함.
        // 순서3
        query.where(board.title.contains("1")); // where title like...
        // 순서4
        List<Board> list = query.fetch(); // db에서 데이터 가져오기.
        // 순서5
        long count = query.fetchCount(); // 가져온 디비의 갯수 확인.
        return null;
    }
}
