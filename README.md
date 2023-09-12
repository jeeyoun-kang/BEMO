#  <img width="40" alt="bemo_icon" src="https://user-images.githubusercontent.com/59076085/227764662-3c13074b-23be-41f3-bc68-cbbc2c13c4c4.png"> BEMO, 영화 추천 및 리뷰 웹서비스 <img width="40" alt="bemo_icon" src="https://user-images.githubusercontent.com/59076085/227764662-3c13074b-23be-41f3-bc68-cbbc2c13c4c4.png">

<br>



# 🚀 [~~BEMO 접속하기~~](http://behindmovie.site)

**유지비용으로 인해 사이트를 임시 폐쇄합니다.**

<br>

## 📃목차 

 - [Project Members](#project-members) 
 - [소개](#소개) 
 - [개발 환경](#개발-환경)
 - [사용 기술](#사용-기술)
 - [시스템 아키텍처](#시스템-아키텍처) 
 - [E-R 다이어그램](#e-r-다이어그램)
 - [프로젝트 목적](#프로젝트-목적)
 - [화면 구성](#화면-구성)
 - [핵심 기능](#핵심-기능)
   - [로그인/회원가입](#로그인회원가입)
   - [프로필](#프로필)
   - [게시글 CRUD](#게시글-crud)
   - [영화/해시태그 검색](#영화해시태그-검색)
   - [영화 상세 페이지](#영화-상세-페이지)
   - [영화 메인 페이지](#영화-메인-페이지)
 - [CI/CD](#cicd)
   - [무중단 배포](#무중단-배포)
 - [도메인 적용](#도메인-적용)
 - [Trouble Shooting](#trouble-shooting)
 - [UI reference license](#ui-reference-license)

## 🌻Project Members

<table>
   <tr>
    <td align="center"><b><a href="https://github.com/jeeyoun-kang">강지윤</a></b></td>
    <td align="center"><b><a href="https://github.com/JYJProgram">장예준</a></b></td>
  <tr>
     <td align="center"><a href="https://github.com/jeeyoun-kang"><img src="https://avatars.githubusercontent.com/u/59076085?v=4" width="100px" /></a></td>
    <td align="center"><a href="https://github.com/JYJProgram"><img src="https://avatars.githubusercontent.com/u/33517050?v=4" width="100px" /></a></td>
  </tr>
  <tr>
    <td align="center"><b>Web Developer</b></td>
    <td align="center"><b>Web Developer</b></td>
</table>




## 소개

**BEMO**는 JAVA, Spring boot를 기반으로 만든 영화 추천 및 리뷰 웹 서비스입니다. <br>



## 개발 환경

 - Windows
 - IntelliJ 
 - GitHub
 - AWS

## 사용 기술 

![java](https://img.shields.io/badge/Java-11-DEB887?style=flat)&nbsp;![springboot](https://img.shields.io/badge/SpringBoot-2.7.3-3CB371?style=flat&logo=springboot)&nbsp;![spriongsecurity](https://img.shields.io/badge/SpringSecurity-5-3CB371?style=flat&logo=springsecurity)

![spriongsecurity](https://img.shields.io/badge/Html5-C0C0C0?style=flat&logo=HTML5)&nbsp; ![spriongsecurity](https://img.shields.io/badge/CSS3-blue?style=flat&logo=css3)&nbsp; ![spriongsecurity](https://img.shields.io/badge/Thymeleaf-green?style=flat&logo=thymeleaf)

![gradle](https://img.shields.io/badge/Gradle-7.5-skyblue?style=flat&logo=gradle)

![mysql](https://img.shields.io/badge/MySQL-8.0.28-FFA07A?style=flat&logo=mysql)

![ec2](https://img.shields.io/badge/AWS-ec2-FF8C00?style=flat&logo=amazonec2)&nbsp;![s3](https://img.shields.io/badge/AWS-s3-FF8C00?style=flat&logo=amazons3)&nbsp;![AWS-RDS](https://img.shields.io/badge/AWS-RDS-FF8C00?style=flat&logo=amazonrds)&nbsp;![w](https://img.shields.io/badge/AWS-CodeDeploy-FF8C00?style=flat&logo=codedeploy)&nbsp;![rds](https://img.shields.io/badge/AWS-Route53-FF8C00?style=flat&logo=amazoneroute53)

**백엔드**

  - Java 11 openjdk
  - SpringBoot 2.7.3
  - Spring Security
  - Spring Data JPA 
  - Lombok

**프론트엔드**

 -   Html5/css3
 -   Javascript
 -   Thymeleaf

**빌드 툴**

 - Gradle 7.5

**데이터베이스**

 - Mysql

**인프라** 

-   AWS EC2
-   AWS S3
-   AWS RDS
-   AWS Codedeploy
-   AWS Route 53
-   Github Actions

**라이브러리**

 - [tagify](https://yaireo.github.io/tagify/)
 - [KOBIS Api](https://www.kobis.or.kr/kobisopenapi/homepg/main/main.do)
 - [Naver search movie Api](https://developers.naver.com/docs/serviceapi/search/movie/movie.md)
 - [Naver datalab Api](https://developers.naver.com/products/service-api/datalab/datalab.md)
 - [Naver login Api](https://developers.naver.com/docs/login/api/api.md)
 - [Kakao Oauth Api](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api)
 - [Google Oauth Api](https://developers.google.com/identity/protocols/oauth2?hl=ko)

 

## 시스템 아키텍처

![bemo_architecture](https://github.com/jeeyoun-kang/BEMO/assets/59076085/b48c6086-d523-49d2-ae74-4b06caf687cc)



## E-R 다이어그램

![bemo_er](https://user-images.githubusercontent.com/59076085/227763346-93bf3e10-2c66-44f4-b01e-1149311cc23a.png)



## 프로젝트 목적

프로젝트를 설계하고 배포과정까지 구현하고  실서비스가 가능한 웹 서비스를  만드는 것과 서비스를 구현하면서  JAVA&SpringBoot에 관한 지식을 습득하는 것


## 💻화면 구성

| ![main](https://user-images.githubusercontent.com/59076085/227449173-631defa9-531c-4ca5-a7ed-cdd0d7ef75a6.JPG) | ![로그인페이지](https://user-images.githubusercontent.com/59076085/223621560-ee22e39a-fe4e-4f16-9e8f-97d4311fa8e1.JPG) | ![회원가입페이지](https://user-images.githubusercontent.com/59076085/223621797-24fa4508-6979-469a-97ba-d32521f94d3e.JPG) |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                         메인 페이지                          |                            로그인                            |                           회원가입                           |

| ![profile](https://user-images.githubusercontent.com/59076085/227449454-3c850ee7-3aaf-4b77-950f-f3d505fd3619.JPG) | ![moviedetail](https://user-images.githubusercontent.com/59076085/227449678-e8681444-c4b1-4bc2-a168-22ca2b153df9.JPG) | ![detail](https://user-images.githubusercontent.com/59076085/227449854-92346716-8356-4e0b-8cda-01a6ff9954aa.JPG) | ![영화검색페이지](https://user-images.githubusercontent.com/59076085/223627541-c60b36eb-563d-473d-89b5-60fa75bf784d.JPG) |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                        프로필 페이지                         |                       영화 상세 페이지                       |                       리뷰 작성 페이지                       |                 영화 / 해시태그 검색 페이지                  |

<br>

## 핵심 기능

### 로그인/회원가입

- 로그인과 회원가입은 팝업창으로 구현했고, Spring Security (SecurityFilterChain, WebSecurityCustomizer) 로 사용자 인증, 접근, 세션 관리를 구현하고 OAuth2를 이용하여 소셜로그인(네이버, 카카오, 구글)도 가능하게 구현
  - OAuth2Userinfo 인터페이스를 상속받아 네이버,카카오,구글 소셜 로그인 구현
- 회원가입 시 Password,Authentication,User 엔티티에  pw,birth,username 등의 정보들을 저장
- 로그인 시 HTTPSecurity의 formLogin() 메서드를 이용해  .loginProcessingUrl("/login")  로 로그인 인증 후, 인증 성공 시 .defaultSuccessUrl("/") 를 통해 메인 페이지로 리다이렉션하게 구현하고 실패 시 .failureForwardUrl("/error")를 통해 에러페이지로 이동하게 구현
- 소셜로그인으로 로그인 시 .oauth2Login() 메서드를 통해 인증 후 , 로그인 성공시 .userService(customOAuth2UserService) 를 통해 유저 정보로 customOAuth2UserService에서 후처리
- 로그아웃 시 HTTPSecurity의 logout() 메서드를 이용해  .logoutUrl("/logout")로 로그아웃 경로를 설정해주고 , 로그아웃 성공 시 .logoutSuccessUrl("/")를 통해 메인 페이지로 리다이렉션하게 구현 후, .deleteCookies("JSESSIONID") 로 쿠키를 삭제해주고, .invalidateHttpSession(true)로 세션을 무효화


### 프로필

- PrincipalDetails과 연결되어 있는 유저의 username을 통해 유저 정보와 유저가 작성한 게시글을 화면에 출력하도록 구현
  - UserDetails 인터페이스와 OAuth2User 인터페이스를 구현한 PrincipalDetails 클래스를 통해 소셜로그인과 일반 로그인 되어 있는 유저 정보를 가져옴


### 게시글 CRUD

- Spring @RestController를 이용해 RESTful API로 게시글 crud를 구현

![post](https://user-images.githubusercontent.com/59076085/227445915-6dc7d695-23eb-42ab-85c8-3da18112b1a4.gif)

- PrincipalDetails 를 통해 현재 로그인한 유저 정보를 얻어 로그인을 한 유저만 글을 작성할 수 있게 구현
- Ajax를 이용해 파일과 다른 데이터들(제목,내용,해시태그)과 함께 multipart/form-data 형식으로 formData를 이용해 JSON 파라미터를 넘겨 데이터들을 DB에 저장 (파일은 S3에 업로드된 이미지의 url을 저장)
- MultipartFile 인터페이스를 이용해 파일 데이터를 가져온 뒤 ,  AWS S3를 이용해 해당 파일을 업로드 하는 과정에서 Marvin 라이브러리를 활용해 이미지 리사이징을 한 뒤 S3에 업로드하게 구현
- 게시글 작성 시 선택한 파일이 없을 경우 업로드 된 defalut 이미지를 AWS S3에서 가져온 뒤 게시글 DB에 저장해 구현

- 해시태그는 Tagify 라이브러리를 이용했고, 화이트리스트를 이용하여 정해진 태그를 게시글을 작성 시에 최대 2개까지 선택하게 구현

&ensp;**글 수정**

![update](https://user-images.githubusercontent.com/59076085/227448390-f29d5b14-6477-4902-ade1-4aa3ffbbca22.gif)



- PrincipalDetails 를 통해 현재 로그인한 유저 정보를 얻어 자신이 작성한 글만 수정할 수 있게 구현

  

&ensp;&ensp;**글 삭제**

- PrincipalDetails 를 통해 현재 로그인한 유저 정보를 얻어 자신이 작성한 글만 삭제할 수 있게 구현

### 영화/해시태그 검색

영화와 해시태그를 검색할 수 있는 검색바를 구현

- Naver open api 를 이용해 해당 영화의 이미지와 이름의 데이터를 파싱해 구현
- contains()를 이용해 "#"이 앞에 들어갔을때는 해시태그를 검색하게 한 뒤 JPA를 이용해 검색한 해시태그가 태그되어있는 영화를 띄우게 구현


### 영화 상세 페이지

- Naver open api와 KOBIS open api를 이용해 json 데이터를 파싱해 영화 상세 데이터(개봉일,장르,감독 등)을 구현.
- Naver datalab api를 이용해 일주일 간 해당 영화 검색량을 분석한 그래프로 구현
  - [영화 검색량 그래프 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/MovieDetailController.java#L161)

- 게시글 DB를 조회해 해당 영화에 대한 리뷰를 작성한 데이터들을 보이게 구현

### 영화 메인 페이지

![main](https://user-images.githubusercontent.com/59076085/227450896-62c42662-77aa-4952-9160-f94d8d5e55ae.gif)



- 전 날 기준, KOBIS Api + Naver search movie Api 를 이용해 영화의 이름와 이미지를 추출해 박스오피스 TOP8을 구현
- 업데이트순으로 리뷰데이터들을 구현하고 이미지와 제목을 누르면 해당 상세 리뷰데이터를 볼 수 있게 구현
- 리뷰 데이터를 토대로 순위별 해시태그를 구현하고 해당 해시태그를 누르면 태그되어 있는 영화 데이터를 띄우게 구현

<h2>CI/CD</h2>

- master 브랜치에 push가 발생하면 , Github actions가 실행되고 생성한 workflow 설정 파일에 따라 프로젝트 파일을 압축해 빌드 후 빌드 결과물을 S3에 업로드
  - gradle.yml 파일을 통해 빌드 후 zip 파일로 압축, 빌드결과물 S3에 업로드해 실행하고, CodeDeploy와 연결해 자동배포 설정
- S3 버킷에 있는 파일을 대상으로 CodeDeploy에게 배포를 요청하고  S3로부터 zip파일을 받아 압축 해제 후 설정 스크립트에 따라 EC2에 배포를 진행
  - CodeDeploy는 appspec.yml와 deploy.sh에 의해 Flow가 작동해 스크립트들(stop.sh,start.sh,health.sh)이 실행하고, 배포를 진행

### 무중단 배포

Nginx를 이용해서 EC2 내부에 포드를 2개(8081,8082)로 나눠서 무중단 배포를 진행

- appsepec.yml로 인해 설정 스크립트가 실행되고, switch.sh에 의해서 배포 업데이트 시 Nginx가 Reload 되고 포드가 바뀌게 설정


### 도메인 적용

가비아에서 구매한 도메인과 aws Route53을 이용해 도메인 연결



## Trouble Shooting

- 게시글을 업로드할 때 File형식과 text 데이터들의 형식이 다르기에 함께 JSON형식으로 컨트롤러로 전송되지 않는 오류가 발생

  - Blob을 사용해 File은 multipart/form-data, 그 외 데이터는 application/json 형식으로 보내 컨트롤러에서 @RequestPart로 분류한 데이터를 받아 문제를 해결

- 게시글의 파일을 업로드하는 과정에서 원본 이미지를 AWS S3에 그대로 업로드 할 경우 이미지 호출 시 화면에 보여지는 이미지 크기 대비 큰 이미지를 호출해 트래픽이 발생

  - Marvin 라이브러리를 이용해 이미지를 리사이징한 뒤 S3에 업로드하는 형식으로 해결

  ```java
  MultipartFile resizeImage(String fileName, String fileFormatName, MultipartFile originalImage, int targetWidth) {
          try {
              // MultipartFile -> BufferedImage Convert
              BufferedImage image = ImageIO.read(originalImage.getInputStream());
              // newWidth : newHeight = originWidth : originHeight
              int originWidth = image.getWidth();
              int originHeight = image.getHeight();
  
              // origin 이미지가 resizing될 사이즈보다 작을 경우 resizing 작업 안 함
              if(originWidth < targetWidth)
                  return originalImage;
  
              MarvinImage imageMarvin = new MarvinImage(image);
  
              Scale scale = new Scale();
              scale.load();
              scale.setAttribute("newWidth", targetWidth);
              scale.setAttribute("newHeight", targetWidth * originHeight / originWidth);
              scale.process(imageMarvin.clone(), imageMarvin, null, null, false);
  
              BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
              ByteArrayOutputStream baos = new ByteArrayOutputStream();
              ImageIO.write(imageNoAlpha, fileFormatName, baos);
              baos.flush();
  
              return new MockMultipartFile(fileName, baos.toByteArray());
  
          } catch (IOException e) {
              throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 리사이즈에 실패했습니다.");
          }
      }
  ```

  

- 해시태그를 검색할 때 해당 해시태그가 태그된 영화를 띄우게 구현했지만 영화에 대한 중복처리를 하지많아 중복된 영화가 화면에 띄워지는 문제가 발생

  - JPA의 JPQL를 이용해 “DISTINCT”로 같은 영화에 대해 중복되지 않게 해결

  ```java
  @Query("select DISTINCT h.mvtitle from Hashtags h where h.content like %:content%")
      List<String> findMvtitleByContentContaining(String content);
  ```

- WebSecurity 구현 중 Spring Security 5.7.x 부터 WebSecurityConfigurerAdapter가 Deprecated되어 사용하지 못하는 문제가 발생

  - SecurityFilterChain과 WebSecurityCustomizer 를 빈으로 등록해 상황에 따라 사용되게 구현

- DB를 설계하는 과정에서 여러 개로 분산된 데이터베이스에 원하는 데이터를 저장 및 추출하는 과정에서 문제가 발생

  -  [회원 가입 및 로그인을 위한 테이블 설계](https://rastalion.me/%ED%9A%8C%EC%9B%90-%EA%B0%80%EC%9E%85-%EB%B0%8F-%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%9D%84-%EC%9C%84%ED%95%9C-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%84%A4%EA%B3%84/)를 통해 정규화된 DB를 구축해 문제를 해결
  -  테이블당 Repository를 각각 만들어준 후에 Dto를 통해 입력된 유저 데이터를 Service에 넘겨주고, Service에서 Dto에 담긴 데이터를 토대로 new Entity하여 Repository에 save하도록 구현

---

### UI reference license

Copyright (c) 2023 by John Fink (https://codepen.io/johnfinkdesign/pen/XjZBPE)

Copyright (c) 2023 by Bradley Engelhardt (https://codepen.io/SquishyAndroid/pen/XjRPVV)

Copyright (c) 2023 by Ian Lunn (https://codepen.io/IanLunn/pen/AxBReL)

Copyright (c) 2023 by Peter Wiebe (https://codepen.io/pjwiebe/pen/VmmxpM)

Copyright (c) 2023 by Austin (https://codepen.io/AustinAuth/pen/xxmbZX)
