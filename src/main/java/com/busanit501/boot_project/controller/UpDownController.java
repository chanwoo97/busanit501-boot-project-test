package com.busanit501.boot_project.controller;

import com.busanit501.boot_project.dto.upload.UploadFileDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class UpDownController {

    @Tag(name = "파일등록" ,  description = "멀티파트 타입 형식으로, 업로드 테스트, post 방식")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        log.info("UpDownController에서 작업중, upload메소드에서, uploadFileDTO 확인 : " + uploadFileDTO);
        return null;
    }

}
