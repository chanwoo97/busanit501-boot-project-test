package com.busanit501.boot_project.service;

import com.busanit501.boot_project.dto.MemberJoinDTO;
import com.busanit501.boot_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper; // dto <-> entity  타입 변환기
    private final MemberRepository memberRepository; // 디비에 로직처리 도구 crud
    private final PasswordEncoder passwordEncoder;  // 평문 패스워드, -> 암호화하는 도구.

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {

        // 1. 회원 가입하는 아이디가 존재하는지 확인.
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);
        if(exist){
            throw new MidExistException();
        }

        // 2. 회원 가입 가능하면, 진행.

    }
}
