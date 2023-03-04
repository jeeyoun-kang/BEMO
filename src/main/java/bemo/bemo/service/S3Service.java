package bemo.bemo.service;

import bemo.bemo.entity.Posts;
import bemo.bemo.repository.PostsRepository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    private final PostsRepository postsRepository;


    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        String fileFormatName = multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);


        // resizedFile = resizeImage(fileName,fileFormatName,multipartFile,250);
        //파일 형식 구하기
        String ext = fileName.split("\\.")[1];
        String contentType = "";

        //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작됨.
        switch (ext) {
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "txt":
                contentType = "text/plain";
                break;
            case "csv":
                contentType = "text/csv";
                break;
        }

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);

            //amazonS3.putObject(new PutObjectRequest(bucket, fileName, resizedFile.getInputStream(), metadata)
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        //object 정보 가져오기
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();

        for (S3ObjectSummary object : objectSummaries) {
        }

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public String getThumbnailPath(String path) {
        return amazonS3.getUrl(bucket, path).toString();
    }

    public void deleteFile(Long id) {
        Posts posts = postsRepository.findPostsByPost_id(id);
        String url = posts.getUrl();
        String result = url.substring(url.lastIndexOf("amazonaws.com/") + 14);
        result = result.replace("%20", " ");
        if (!result.equals("bemo.JPG")) { // default 이미지는 객체 삭제 못하는 조건
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, result));
        }

    }

//    MultipartFile resizeImage(String fileName, String fileFormatName, MultipartFile originalImage, int targetWidth) {
//        try {
//            // MultipartFile -> BufferedImage Convert
//            BufferedImage image = ImageIO.read(originalImage.getInputStream());
//            // newWidth : newHeight = originWidth : originHeight
//            int originWidth = image.getWidth();
//            int originHeight = image.getHeight();
//
//            // origin 이미지가 resizing될 사이즈보다 작을 경우 resizing 작업 안 함
//            if(originWidth < targetWidth)
//                return originalImage;
//
//            MarvinImage imageMarvin = new MarvinImage(image);
//
//            Scale scale = new Scale();
//            scale.load();
//            scale.setAttribute("newWidth", targetWidth);
//            scale.setAttribute("newHeight", targetWidth * originHeight / originWidth);
//            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);
//
//            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(imageNoAlpha, fileFormatName, baos);
//            baos.flush();
//
//            return MultipartFile(fileName, baos.toByteArray());
//
//        } catch (IOException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 리사이즈에 실패했습니다.");
//        }
//    }
}
