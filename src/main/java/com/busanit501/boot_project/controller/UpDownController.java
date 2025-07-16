package com.busanit501.boot_project.controller;

import com.busanit501.boot_project.dto.upload.UploadFileDTO;
import com.busanit501.boot_project.dto.upload.UploadResultDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

    // 업로드 할 , 저장 위치를 불러오기. com.busanit501.upload.path
    @Value("${com.busanit501.upload.path}") // 스프링에서 지원해주는 패키지 경로 사용하기.
    private String uploadPath;

    @Tag(name = "파일등록" ,  description = "멀티파트 타입 형식으로, 업로드 테스트, post 방식")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.info("UpDownController에서 작업중, upload메소드에서, uploadFileDTO 확인 : " + uploadFileDTO);

        if(uploadFileDTO.getFiles() != null && uploadFileDTO.getFiles().size() > 0) {
            
            // 전달할 , 파일들을 담아둘, 컬렉션, 리스트를 정의, 
            final List<UploadResultDTO> list = new ArrayList<>();
            
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                log.info("업로드한 파일명 : "+multipartFile.getOriginalFilename());
                String originalName = multipartFile.getOriginalFilename();
                // 업로드한 파일명, 중복 방지, UUID사용하기.
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid+"_"+originalName);
                
                // 파일 타입의 상태 변수
                boolean image = false;
                
                try {
                    multipartFile.transferTo(savePath); // 실제 파일 저장.

                    // 만약, 업로드한 파일이 이미지이면, 썸네일 이미지로 변환하기.
                    if(Files.probeContentType(savePath).startsWith("image")) {
                        // 상태변수 변경
                        image = true;
                        // 썸네일 변환하기.
                        File thumbFile = new File(uploadPath,"s_" + uuid+"_"+originalName);
                        // 썸네일 변환 도구 이용.
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }

                // 현재 반복문 안에서 작업중. , 각 업로드 파일들을 하나씩 리스트에 담기 작업.
                UploadResultDTO dto = UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(image)
                        .build();

                list.add(dto);

            }); // end for each
            return list;
        } // end if

        return null;
    }

}
