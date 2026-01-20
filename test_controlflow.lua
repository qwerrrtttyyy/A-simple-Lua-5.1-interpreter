-- 测试控制流语句

print("=== 测试 if 语句 ===");
local x = 10;
if (x > 5) then
    print("x is greater than 5");
end

if (x < 5) then
    print("x is less than 5");
else
    print("x is not less than 5");
end

if (x > 15) then
    print("x is greater than 15");
elseif (x == 10) then
    print("x equals 10");
else
    print("x is something else");
end

print("=== 测试 while 循环 ===");
local i = 1;
while (i <= 5) do
    print(i);
    i = i + 1;
end

print("=== 测试 for 循环 ===");
for j = 1, 10 do
    print(j);
end

print("=== 测试 for 循环（步长） ===");
for k = 1, 10, 2 do
    print(k);
end

print("=== 测试 repeat-until 循环 ===");
local m = 0;
repeat
    m = m + 1;
    print(m);
until (m > 5)

print("=== 测试 break 语句 ===");
local n = 0;
while (n < 10) do
    n = n + 1;
    if (n == 5) then
        break
    end
    print(n);
end

print("=== 测试嵌套循环 ===");
for a = 1, 3 do
    for b = 1, 3 do
        print(a .. " x " .. b .. " = " .. (a * b));
    end
end

print("=== 测试完成 ===");