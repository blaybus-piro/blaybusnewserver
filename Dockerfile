FROM openjdk:17

# 빌드된 JAR 파일의 경로 지정
ARG JAR_FILE=build/libs/*.jar

# JAR 파일을 컨테이너로 복사
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]