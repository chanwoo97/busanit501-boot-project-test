package com.busanit501.boot_project.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserService에서, 인증한 유저 정보 확인 userRequest : "  + userRequest);

        // 동의항목에 동의한 수집 정보 3가지중에서, 일단 1) 닉네임 2) 프로필 이미지 링크 주소 3) 이메일
        // 이정보를 서버에서 사용하기 쉽게 , 데이터 변환 작업 하기.
        // userRequest 여기 객체에 들어가 있다. ====================전달 받은 정보 모두 사용 하는것은 아니다, 필요한 것만 뽑아 사용하기.
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.info("clientName 확인 : "+ clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String,Object> paramMap = oAuth2User.getAttributes();
        paramMap.forEach( (key,value) -> {
            log.info("==========oAuth2User.getAttributes() -> paramMap -> 해당 속성 조회 해보기. ============================");
            log.info("key : "+ key + ", value : " + value);
        });

        return super.loadUser(userRequest);
    }
}
// 참고, 전달 받은 정보의 객체 트리 구조,
// ===========================================
// [Principal=Name: [4370163525],
// Granted Authorities: [[OAUTH2_USER, SCOPE_account_email, SCOPE_profile_image, SCOPE_profile_nickname]],
// User Attributes: [{id=4370163525,
// connected_at=2025-07-28T02:27:44Z,
// properties={nickname=이상용,
// profile_image=http://k.kakaocdn.net/dn/si5pD/btsNAKP3qpo/k980AtXbaNVWWGtxirbYR1/img_640x640.jpg,
// thumbnail_image=http://k.kakaocdn.net/dn/si5pD/btsNAKP3qpo/k980AtXbaNVWWGtxirbYR1/img_110x110.jpg},
// kakao_account={profile_nickname_needs_agreement=false,
// profile_image_needs_agreement=false,
// profile={nickname=이상용,
// thumbnail_image_url=http://k.kakaocdn.net/dn/si5pD/btsNAKP3qpo/k980AtXbaNVWWGtxirbYR1/img_110x110.jpg,
// profile_image_url=http://k.kakaocdn.net/dn/si5pD/btsNAKP3qpo/k980AtXbaNVWWGtxirbYR1/img_640x640.jpg,
// is_default_image=false, is_default_nickname=false},
// has_email=true, email_needs_agreement=false, is_email_valid=true,
// is_email_verified=true, email=lsy3709@kakao.com}}], Credentials=[PROTECTED],
// Authenticated=true,
// Details=WebAuthenticationDetails
// [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=3327CB27ECAE85E8C7CEF0D9FD0997F5],
// Granted Authorities=[OAUTH2_USER, SCOPE_account_email, SCOPE_profile_image, SCOPE_profile_nickname]]]
// to HttpSession [org.apache.catalina.session.StandardSessionFacade@5c5d52af]
