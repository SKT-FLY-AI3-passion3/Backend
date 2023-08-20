# Backend

# STT Service

  localhost:8080/convert
  POST
  form-data
  key=file, value=mp3file

# Github Action 적용  
  main branch 에 Push 시 Dockerfile 통해 이미지 제작 후 도커 허브에 업로드
  환경 jdk17, gradle, Linux
  Docker hub 주소 : jaehyuk00/passion3backend
