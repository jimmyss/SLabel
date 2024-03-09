# 基于 OpenJDK 官方镜像
FROM openjdk:17 as build

# 设置工作目录
WORKDIR /workspace/app/backend

# 复制 gradle/wrapper、gradlew 和 build.gradle.kts 文件或目录
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 复制源代码
COPY src src

# 构建应用
RUN ./gradlew build -x test

# 运行阶段
FROM openjdk:11-jre-slim

# 将构建产物复制到容器中
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# 暴露端口
EXPOSE 8080

# 设置启动命令
ENTRYPOINT ["java","-jar","/app.jar"]
