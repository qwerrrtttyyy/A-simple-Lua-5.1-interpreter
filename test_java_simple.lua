-- 测试 Java 互操作（简单版）

print("=== 测试 Java 互操作 ===");

-- 测试 Java Math.sqrt
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

print("\n=== 测试完成 ===");