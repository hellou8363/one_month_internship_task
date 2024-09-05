# one month internship task
한 달 인턴 과제 제출용 

## 목차
- [요구사항](#요구사항)
    - [회원가입 Request, Response](#회원가입-request-response)
    - [로그인 Request, Response](#로그인-request-response)
- [API 명세서](#api-명세서)



## 요구사항
+ 유닛 테스트 완성
+ 회원가입 구현
+ 로그인 구현
+ AWS EC2에 배포
+ Swagger UI로 접속 가능하게 하기

### 회원가입 Request, Response
회원가입: Request Message
```json
{
    "username": "JIN HO",
    "password": "12341234",
    "nickname": "Mentos"
}
```
회원가입: Response Message
```json
{
    "username": "JIN HO",
    "nickname": "Mentos", 
    "authorities": [
        {
            "authorityName": "ROLE_USER"
        }
    ]		
}
```
### 로그인 Request, Response
로그인: Request Message
```json
{
    "username": "JIN HO", 
    "password": "12341234"
}
```
로그인: Response Message
```json
{
    "token": "eKDIkdfjoakIdkfjpekdkcjdkoIOdjOKJDFOlLDKFJKL"
}
```

## API 명세서
Swagger UI 접속 주소: http://3.36.119.210/swagger-ui/index.html

![image](https://github.com/user-attachments/assets/377f53d2-90d9-48fa-815d-78f90c7b02b9)