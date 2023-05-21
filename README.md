## 1. 개요

### 1.1 팀 정보

| 👨‍💻 팀원명 | 📁 역할 |
| --- | --- |
| 한성준 | 회원 서비스 |
| 백수진 | 메인, 지도 서비스 |

### 1.2 주제

사진 작가들을 위한 날씨 변화에 따른 포토 스팟 등급별 추천 서비스

### 1.3 개발 환경 및 사용 기술

| 사용 기술| 종류 |
| --- | --- |
| 프레임워크 | Spring, React |
| 데이터베이스 | MySQL |
| 버전 관리 | Git |
| 개발 환경 | IntelliJ |
| Build Tool | Gradle |

### **1.4 프로젝트 세팅**

**`api.yml`**

```java
api:
  weather:
    endPoint: http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst
    serviceKey: [본인의 serviceKey를 입력]
  particulate-matter:
    predict:
      endPoint: http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth
    endPoint: http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty
    serviceKey: [본인의 serviceKey를 입력]
```

**`application.yaml`**

```java
spring:
  datasource:
    driver-class-name: # DB Class Name
    url: [본인의 mysql-rds-endpoint]
    password: [DB password]

  jpa:
    database: mysql
    open-in-view: true
    hibernate:
      ddl-auto: create #운영장비에서는 절대 사용해서는 안됨
    show-sql: true
    properties:
      hibernate.format_sql: true
      dialect: org.hibernate.dialect.MySQL5InnoDBDialect
    defer-datasource-initialization: true

  sql:
    init:
      mode: always

logging:
  level:
    org.hibernate.SQL: debug
```

**`email.properties`**

```java
AdminMail:
  password: [발급받은 구글 앱 Key]
  id: [본인 Google Email]
mail:
  smtp:
    socketFactory:
      class: javax.net.ssl.SSLSocketFactory
      fallback: 'false'
      port: '465'
    starttls:
      required: 'true'
      enable: 'true'
    port: '465'
    auth: 'true'
```

---
