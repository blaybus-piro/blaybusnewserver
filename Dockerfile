name: CI-CD-Demo

on:
  push:
    branches: [ "main" ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

    steps:
      # (1) 코드 체크아웃
      - name: Check out repository
        uses: actions/checkout@v2

      # (2) 도커 빌드
      - name: Build Docker Image
        run: |
          docker build -t chltmdgh522/blarybus:latest .

      # (3) DockerHub 로그인
      - name: DockerHub Login
        run: |
          echo "${{ secrets.DOCKERHUB_PASSWORD }}" | docker login -u "${{ secrets.DOCKERHUB_USERNAME }}" --password-stdin

      # (4) 도커 이미지 푸시
      - name: Push to DockerHub
        run: |
          docker push chltmdgh522/blarybus:latest

      # (5) SSH를 통해 서버에 접속하여 컨테이너 실행
      - name: Deploy to Server
        uses: appleboy/ssh-action@master
        with:
          host: ${{ secrets.SERVER_HOST }}            # 서버 도메인 or IP
          username: ${{ secrets.SERVER_USERNAME }}     # 예: ubuntu
          key: ${{ secrets.SERVER_SSH_KEY }}           # Private Key
          script: |
            # 이미 돌아가는 컨테이너가 있다면 중단 및 제거
            docker stop blarybus || true
            docker rm blarybus || true

            # 이미지 최신화
            docker pull chltmdgh522/blarybus:latest

            # 새 컨테이너 실행 (메모리/CPU, 재시작, ENV 포함)
            docker run -d \
              --name blarybus \
              -p 8080:8080 \
              --cpus="1.0" \
              --memory="512m" \
              --memory-swap="1g" \
              --restart=always \
              -e SPRING_DATASOURCE_URL='${{ secrets.SPRING_DATASOURCE_URL }}' \
              -e SPRING_DATASOURCE_USERNAME='${{ secrets.SPRING_DATASOURCE_USERNAME }}' \
              -e SPRING_DATASOURCE_PASSWORD='${{ secrets.SPRING_DATASOURCE_PASSWORD }}' \
              chltmdgh522/blarybus:latest
