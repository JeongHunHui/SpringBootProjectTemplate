# 템플릿을 활용한 프로젝트 초기 세팅하기

1. gradle.properties 와 .env 추가하기
    
    gradle.properties (jib을 이용한 스프링부트 컨테이너 제작을 위한 설정파일)
    
    ```yaml
    DOCKER_ID=본인의_도커허브_아이디
    DOCKER_IMAGE_NAME=도커_이미지_이름(마음대로)
    DOCKER_PASSWORD=본인의_도커허브_비밀번호
    ```
    
    .env
    
    ```yaml
    POSTGRES_URL=jdbc:postgresql://postgresql:5432/DB이름
    POSTGRES_USER=DB유저이름
    POSTGRES_PASSWORD=DB비밀번호
    POSTGRES_DB=DB이름
    SPRING_BOOT_IMAGE=도커허브_아이디(gradle.properties와 동일하게)/도커_이미지_이름(gradle.properties와 동일하게):latest
    ```
    
2. gradle 빌드하기 → build.gradle 우클릭 → Link Gradle Project 클릭
3. 패키지 이름 변경하기
    
    ![image](https://user-images.githubusercontent.com/108508730/205462313-f2112b66-058d-41ae-94c5-a79eb5662848.png)
    
    IntelliJ를 통해 변경하면 클래스에 있는 패키지 정보도 다 업데이트된다(위의 사진에서 All Directories 클릭)
    
4. 기타 파일 변경하기
    - LICENSE
    - build.gradle 에서 group
    - setting.gradle
    - .gitmodules 에서 application.yml을 담은 레포지토리 주소 넣어두기
        - 민감한 정보를 담고있는 application.yml을 서브모듈과 private repository로 관리
            
            서브모듈 레포에 넣을 application.yml
            
            ```yaml
            spring:
              jpa:
                show_sql: true
                hibernate:
                  dialect: org.hibernate.dialect.PostgreSQLDialect
                  ddl-auto: create
                generate-ddl: true
                properties:
                  hibernate:
                    format_sql: true
              datasource:
                url:
                  jdbc:postgresql://localhost:5432/DB명
                username:
                  DB유저이름
                password:
                  DB비밀번호
                driver-class-name: org.postgresql.Driver
              sql:
                init:
                  platform: postgres
            ```
            
            → 서브모듈 귀찮으면 이 내용을 application.yml에 넣기
            
    - SwaggerConfig 파일의 프로젝트이름과 패키지 이름 바꾸기
    - Application 클래스와 ApplicationTest 클래스 이름 바꾸기
5. Application 실행해서 로컬에서 잘 돌아가는지 확인
6. `./gradlew jib` 명령어로 jib build 하기
7. `docker compose up —build` 명령어로 docker 실행
