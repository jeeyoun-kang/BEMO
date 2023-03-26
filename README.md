


#  <img width="40" alt="bemo_icon" src="https://user-images.githubusercontent.com/59076085/227764662-3c13074b-23be-41f3-bc68-cbbc2c13c4c4.png"> BEMO, 영화 추천 및 리뷰 웹서비스 <img width="40" alt="bemo_icon" src="https://user-images.githubusercontent.com/59076085/227764662-3c13074b-23be-41f3-bc68-cbbc2c13c4c4.png">

<br>



# 🚀 [BEMO 접속하기](http://behindmovie.site)

<br>

## 📃목차 

 - [Project Members](#project-members) 
 - [소개](#소개) 
 - [개발 환경](#개발-환경)
 - [사용 기술](#사용-기술)
 - [시스템 아키텍처](#시스템-아키텍처) 
 - [E-R 다이어그램](#e-r-다이어그램)
 - [API 명세서](#api-명세서)
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

**BEMO**는 megabox와 왓챠피디아를 참고해 JAVA, Spring boot를 기반으로 만든 영화 추천 및 리뷰 웹 서비스입니다. <br>



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

![architecture drawio](https://user-images.githubusercontent.com/59076085/223635932-e928fe62-0250-4983-b775-a3497bcbd774.png)



## E-R 다이어그램

![bemo_er](https://user-images.githubusercontent.com/59076085/227763346-93bf3e10-2c66-44f4-b01e-1149311cc23a.png)


## API 명세서

### [Api 명세서 보기](https://app.swaggerhub.com/apis-docs/jeeyoun-kang/BEMO_API/1.0.0)



## 프로젝트 목적

프로젝트를 설계하고 배포과정까지 구현하고  실서비스가 가능한 웹 서비스를  만드는 것과 서비스를 구현하면서  JAVA&SpringBoot에 관한 지식을 습득하는 것이 주 목적입니다.


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

- 회원가입 시에 입력된 정보를 DB에 저장하도록 구현하였습니다.
- Spring Security (SecurityFilterChain, WebSecurityCustomizer) 를 이용하여 사용자 인증, 접근, 세션 관리를 구현하였습니다.
- OAuth2를 이용하여 소셜로그인(네이버, 카카오, 구글)을 통한 로그인 / 회원가입이 가능하게 구현하였습니다.


### 프로필

- 현재 로그인되어있는 사용자의 ID를 통해 DB에서 간단한 정보를 가져와 화면에 출력하도록 구현하였습니다.

### 게시글 CRUD

- Spring @RestController를 이용해 RESTful API로 게시글 crud를 구현하였습니다.
  - [RESTful API로 게시글 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/ApiController.java#L38)

&ensp;&ensp;**글작성**

![post](https://user-images.githubusercontent.com/59076085/227445915-6dc7d695-23eb-42ab-85c8-3da18112b1a4.gif)



- [ 로그인을 해야 글을 작성할 수 있게 Spring Security를 이용해 구현하였습니다. ](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/ReviewController.java#L24 )
- Ajax를 이용해 파일과 다른 데이터들(제목,내용,해시태그)과 함께 multipart/form-data 형식으로 formData를 이용해 JSON 파라미터를 넘겨 해당 데이터들을 DB에 저장하는 방식을 이용했습니다. 
- MultipartFile을 이용해 파일 데이터를 가져온 뒤 ,  AWS S3를 이용해 해당 파일을 업로드 하는 과정에서 Marvin 라이브러리를 활용해 이미지 리사이징을 한 뒤 S3에 업로드하게 구현하였습니다.
- [ tagify 라이브러리를 이용해서 해시태그를 구현하였습니다.](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/resources/static/js/review.js#L2 )



&ensp;**글 수정**

![update](https://user-images.githubusercontent.com/59076085/227448390-f29d5b14-6477-4902-ade1-4aa3ffbbca22.gif)



- [자신이 작성한 게시글만 수정이 가능하도록 Spring Security를 이용해 구현하였습니다. ](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/ApiController.java#L61)
- 선택한 파일이 없을 경우 기존에 설정해 둔 defalut 이미지를 AWS S3에서 가져온 뒤 DB에 저장해 구현하였습니다.

&ensp;&ensp;**글 삭제**

- 자신이 작성한 게시글만 삭제가 가능하도록 Spring Security를 이용해 구현하였습니다.


### 영화/해시태그 검색

- Naver open api 를 이용해 해당 영화의 이미지와 이름의 데이터를 파싱해 구현하였습니다.
  - [영화 검색](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/SearchController.java#L107)

- contains()를 이용해 "#"이 앞에 들어갔을때는 해시태그를 검색하게 한 뒤 JPA를 이용해 검색한 해시태그가 태그되어있는 영화를 띄우게 구현하였습니다. 
  - [해시태그 검색](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/SearchController.java#L58)


### 영화 상세 페이지

- Naver open api와 KOBIS open api를 이용해 json 데이터를 파싱해 영화 상세 데이터(개봉일,장르,감독 등)을 구현하였습니다.
  - [영화 상세 데이터 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/MovieDetailController.java#L46)
- Naver datalab api를 이용해 일주일 간 해당 영화 검색량을 분석한 그래프로 구현했습니다.
  - [영화 검색량 그래프 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/controller/MovieDetailController.java#L161)
- th:each문을 이용해 db를 조회해 해당 영화에 대한 리뷰를 작성한 데이터들을 보이게 구현하였습니다.
  - [영화 리뷰 데이터 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/resources/templates/moviedetail.html#L198) 

### 영화 메인 페이지

![main](https://user-images.githubusercontent.com/59076085/227450896-62c42662-77aa-4952-9160-f94d8d5e55ae.gif)



- KOBIS api를 이용해 박스오피스 TOP8을 구현하였습니다.
- 영화 또는 해시태그를 검색할 수 있는 검색바를 구현하였습니다.
- 업데이트순으로 리뷰데이터들을 구현하고 이미지와 제목을 누르면 해당 리뷰데이터를 상세하게 볼 수 있게 구현하였습니다. 
  - [업데이트순 리뷰데이터 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/resources/templates/main.html#L196)

- 리뷰 데이터를 토대로 순위별 해시태그를 구현하고 해당 해시태그를 누르면 태그되어 있는 영화 데이터를 띄우게 구현하였습니다.
  - [순위별 해시태그 구현](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/resources/templates/main.html#L185)


<h3>해시태그</h3>

- 해시태그는 JavaScript내에 Tagify를 이용하여 구현하였습니다.
- 화이트리스트를 이용하여 정해진 태그를 게시글을 작성 시에 최대 2개까지 선택하고 선택된 해시태그는 게시글과 함께 DB에 저장됩니다.

<h2>CI/CD</h2>

- master 브랜치에 push가 발생하면 , Github actions가 실행되고 생성한 스크립트 설정 파일에 따라 프로젝트 파일을 압축해 빌드 후 빌드 결과물을 S3에 업로드를 합니다.
-  S3 버킷에 있는 파일을 대상으로 CodeDeploy에게 배포를 요청하고 CodeDeploy는 S3로부터 zip파일을 받아 EC2에 배포를 진행합니다

### 무중단 배포

Nginx와 스크립트를 통해 배포 진행 시 연결하는 포트를 다르게하여(8081/8082) 무중단 배포를 구현하였습니다.

- [스크립트 구현](https://github.com/jeeyoun-kang/BEMO/tree/master/scripts)


### 도메인 적용

가비아에서 구매한 도메인과 aws Route53을 이용해 도메인 연결하였습니다.



## Trouble Shooting

- 게시글을 업로드할 때 Ajax를 이용해 File을 다른 데이터들을 함께 JSON형식으로 컨트롤러로 전송되지 않는 문제가 발생했습니다.
  - [FormData를 이용해 File과 다른 데이터들을 key로 구분시킨 뒤 append를 시켜 multipart/form-data의 형식으로 전송해 문제를 해결하였습니다.](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/resources/static/js/review.js#L77)
- 게시글의 파일을 업로드하는 과정에서 원본 이미지를 AWS S3에 그대로 업로드 할 경우 이미지 호출 시 화면에 보여지는 이미지 크기 대비 큰 이미지를 호출해 트래픽이 발생했습니다.
  - [Marvin 라이브러리를 이용해 이미지를 리사이징한 뒤  S3에 업로드하는 형식으로 해결하였습니다.](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/service/S3Service.java#L45)
- 해시태그를 검색할 때 해당 해시태그가 태그된 영화를 띄우게 구현했지만 같은 영화가 중복되게 화면에 띄워지는 문제가 발생하였습니다.
  - [JPA의 @Query를 이용해 영화가 중복되지 않게 해결하였습니다.](https://github.com/jeeyoun-kang/BEMO/blob/6e77b51efc5920c632cc30ea93a024282234143c/src/main/java/bemo/bemo/repository/HashtagsRepository.java#L17)
- WebSecurity 구현 중 Spring Security 5.7.x 부터 WebSecurityConfigurerAdapter가 Deprecated되어 사용하지 못하는 문제가 발생하였습니다.
  - SecurityFilterChain과 WebSecurityCustomizer 를 빈으로 등록해 구현하였습니다.
- DB를 설계하는 과정에서 여러 개로 분산된 데이터베이스에 원하는 데이터를 저장 및 추출하는 과정에서 문제가 발생하였습니다.
  -  [회원 가입 및 로그인을 위한 테이블 설계](https://rastalion.me/%ED%9A%8C%EC%9B%90-%EA%B0%80%EC%9E%85-%EB%B0%8F-%EB%A1%9C%EA%B7%B8%EC%9D%B8%EC%9D%84-%EC%9C%84%ED%95%9C-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%84%A4%EA%B3%84/)를 보고 정규화의 필요성에 대해 알게되고 정규화된 DB를 구축해 문제를 해결하였습니다.
  -  테이블당 Repository를 각각 만들어준 후에 Dto를 통하여 입력된 유저 데이터를 Service에 넘겨주고, Service에서 Dto에 담긴 데이터를 토대로 new Entity하여 Repository에 save하도록 구현하였습니다.

---

### UI reference license

Copyright (c) 2023 by John Fink (https://codepen.io/johnfinkdesign/pen/XjZBPE)

Copyright (c) 2023 by Bradley Engelhardt (https://codepen.io/SquishyAndroid/pen/XjRPVV)

Copyright (c) 2023 by Ian Lunn (https://codepen.io/IanLunn/pen/AxBReL)

Copyright (c) 2023 by Peter Wiebe (https://codepen.io/pjwiebe/pen/VmmxpM)

Copyright (c) 2023 by Austin (https://codepen.io/AustinAuth/pen/xxmbZX)
