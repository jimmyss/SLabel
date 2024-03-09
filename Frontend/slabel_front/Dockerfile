# 基于 Node.js 官方镜像
FROM node:18 as build-stage

# 设置工作目录
WORKDIR /workspace/app/frontend

# 复制 package.json 和 package-lock.json 文件
COPY package*.json ./

# 安装依赖
RUN npm install

# 复制项目文件
COPY . .

# 构建应用
RUN npm run build

# 生产阶段，使用 Nginx
FROM nginx:stable-alpine as production-stage

# 从构建阶段复制构建结果到 Nginx 用于托管的目录
COPY --from=build-stage /app/dist /usr/share/nginx/html

# 暴露 80 端口
EXPOSE 80

# 启动 Nginx 服务器
CMD ["nginx", "-g", "daemon off;"]
