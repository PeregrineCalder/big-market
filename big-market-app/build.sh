# 普通镜像构建，随系统版本构建 amd/arm
 docker build -t peregrinecalder/big-market-app:2.0 -f ./Dockerfile .
# docker build --platform linux/amd64 -t peregrinecalder/big-market-app:2.0 .
# 兼容 amd、arm 构建镜像
# docker buildx build -t peregrinecalder/big-market-app:2.0 -f ./Dockerfile . --platform liunx/amd64,linux/arm64 --push