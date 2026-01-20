-- 测试变量系统

print("=== 测试变量赋值 ===");
x = 10;
print(x);

print("=== 测试变量重新赋值 ===");
x = x + 5;
print(x);

print("=== 测试局部变量 ===");
local y = 20;
print(y);

print("=== 测试局部变量赋值 ===");
y = y * 2;
print(y);

print("=== 测试多个变量 ===");
local a = 1;
local b = 2;
local c = a + b;
print(c);

print("=== 测试变量在表达式中 ===");
local z = 15;
print(z * 2);
print(z / 3);
print(z + 10);
print(z - 5);

print("=== 测试完成 ===");