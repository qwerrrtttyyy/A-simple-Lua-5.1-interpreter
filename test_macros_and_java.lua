-- 测试宏系统和 Java 互操作

print("=== 测试宏系统 ===");

-- 测试 unless 宏
print("\n--- unless 宏 ---");
local x = 5;
unless x > 10 do
    print("x is not greater than 10");
end

-- 测试 when 宏
print("\n--- when 宏 ---");
local y = 15;
when y > 10 do
    print("y is greater than 10");
end

-- 测试 let 宏
print("\n--- let 宏 ---");
let a = 10, b = 20 do
    print("a + b = " .. (a + b));
end

print("=== 测试循环宏 ===");

-- 测试 times 宏
print("\n--- times 宏 ---");
times 3 do
    print("Hello");
end

print("\n=== 测试 Java 互操作 ===");

-- 测试 Java Math 库
print("\n--- Java Math.sqrt ---");
local result = java "java.lang.Math" "sqrt" 16.0;
print("sqrt(16) = " .. result);

-- 测试 Java Math.abs
print("\n--- Java Math.abs ---");
local abs = java "java.lang.Math" "abs" -5.0;
print("abs(-5) = " .. abs);

-- 测试 Java Math.max
print("\n--- Java Math.max ---");
local max = java "java.lang.Math" "max" 10.0 20.0;
print("max(10, 20) = " .. max);

-- 测试 Java String.toUpperCase
print("\n--- Java String.toUpperCase ---");
local upper = java "java.lang.String" "toUpperCase" "hello";
print("toUpperCase('hello') = " .. upper);

print("\n=== 测试完成 ===");