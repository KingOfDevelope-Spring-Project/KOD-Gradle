package com.koreait.kod.controller.user.review;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
@RequestMapping("/reviewImages/*")
public class xxxxFileUploadController {
    //    파일 업로드
    @PostMapping("upload")
    @ResponseBody
    public List<String> upload(@RequestParam("uploadReivewImage") List<MultipartFile> uploadReviewImages) throws IOException {
        String path = "C:/upload/" + getPath();
        List<String> uuids = new ArrayList<>();
        File file = new File(path);
        if(!file.exists()){file.mkdirs();}

        for (int i=0; i<uploadReviewImages.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            uploadReviewImages.get(i).transferTo(new File(path, uuids.get(i) + "_" + uploadReviewImages.get(i).getOriginalFilename()));
            if(uploadReviewImages.get(i).getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + uploadReviewImages.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(uploadReviewImages.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        return uuids;
    }

    public String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    //    파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String reviewImageName) throws IOException{
//        Path path = Paths.get("C:/upload/"+reviewImageName);
//        return Files.readAllBytes(path);
        log.info( reviewImageName);
        return FileCopyUtils.copyToByteArray(new File("C:/upload/", reviewImageName));
    }

    //    파일 다운로드
    @GetMapping
    public ResponseEntity<Resource> download(String reviewImageName) throws UnsupportedEncodingException {
        Resource resource = new FileSystemResource("C:/upload/" + reviewImageName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + new String(reviewImageName.substring(reviewImageName.indexOf("_") + 1).getBytes("UTF-8"), "ISO-8859"));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


}
