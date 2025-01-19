package blarybus.blarybus.global.file;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileStore {
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName; // 버킷 이름 설정

    private final AmazonS3Client amazonS3Client;

    public String storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        // 원본 파일 이름 가져오기
        String originalFilename = multipartFile.getOriginalFilename(); // 예: image.png

        // 저장할 파일 이름 생성
        String storeFileName = createStoreFileName(originalFilename); // 고유 파일 이름 생성

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());

        // S3에 파일 업로드
        amazonS3Client.putObject(new PutObjectRequest(bucketName, storeFileName, multipartFile.getInputStream(), metadata));


        // 업로드된 파일의 URL 생성
        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucketName,               // S3 버킷 이름
                "ap-northeast-2",         // S3 리전 (필요에 따라 변경)
                storeFileName             // 저장된 파일 이름
        );

        return fileUrl;
    }



    private static String createStoreFileName(String originalFilename) {
        String ext = extractedExt(originalFilename);
        // 서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        //asd3f143as5d4f5.png

        String storeFileName = uuid + "." + ext;

        return storeFileName;
    }

    private static String extractedExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1); //png반환
        return ext;
    }
}
