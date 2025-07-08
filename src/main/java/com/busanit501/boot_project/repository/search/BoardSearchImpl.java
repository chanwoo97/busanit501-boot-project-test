package com.busanit501.boot_project.repository.search;

import com.busanit501.boot_project.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

// 인터페이스이름 + Impl, 이름 규칙, 동일하게 작성,
// QuerydslRepositorySupport : 부모클래스, Querydsl 사용하기 위한 도구함.
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        // 부모 클래스에서, 엔티티 클래스 전달. 사용할 클래스 지정.
        super(Board.class);
    }

    @Override
    public Page<Board> search(Pageable pageable) {
        return null;
    }
}
