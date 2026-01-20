#!/bin/bash

# Lua 解释器编译脚本
# 包含 Caffeine 和 FastUtil JAR 库

JAR_DIR="/storage/emulated/0/java/lua_interpreter/jar"
CLASSPATH=".:$JAR_DIR/caffeine-3.1.8.jar:$JAR_DIR/fastutil-8.5.13.jar"

echo "编译 Lua 解释器..."
echo "类路径: $CLASSPATH"
echo ""

# 编译所有 Java 文件
find /storage/emulated/0/java/lua_interpreter -name "*.java" -type f | \
  xargs javac -cp "$CLASSPATH" -d /storage/emulated/0/java/lua_interpreter

echo "编译完成！"
echo ""
echo "运行程序："
echo "  java -cp \"$CLASSPATH\" Home [script.lua]"
echo "  java -cp \"$CLASSPATH\" Test"