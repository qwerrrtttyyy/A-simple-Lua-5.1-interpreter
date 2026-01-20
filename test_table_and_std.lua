-- 测试 Table 类型和标准库

-- 测试 Table 字面量
print("=== 测试 Table 字面量 ===");
t = {1, 2, 3, 4, 5};
print("创建数组: " .. t[1] .. ", " .. t[2] .. ", " .. t[3]);

-- 测试 Table 字典
dict = {name = "Lua", version = "5.1"};
print("字典访问: name = " .. dict.name .. ", version = " .. dict.version);

-- 测试混合 Table
mixed = {1, 2, name = "test", value = 100};
print("混合 Table: " .. mixed[1] .. ", " .. mixed.name);

-- 测试 math 库
print("\n=== 测试 math 库 ===");
print("math.pi = " .. math.pi);
print("math.abs(-5) = " .. math.abs(-5));
print("math.floor(3.7) = " .. math.floor(3.7));
print("math.ceil(3.2) = " .. math.ceil(3.2));
print("math.sqrt(16) = " .. math.sqrt(16));
print("math.max(1, 5, 3) = " .. math.max(1, 5, 3));
print("math.min(1, 5, 3) = " .. math.min(1, 5, 3));

-- 测试 string 库
print("\n=== 测试 string 库 ===");
s = "Hello World";
print("字符串: " .. s);
print("长度: " .. string.len(s));
print("大写: " .. string.upper(s));
print("小写: " .. string.lower(s));
print("子串: " .. string.sub(s, 1, 5));
print("反转: " .. string.reverse(s));

-- 测试 table 库
print("\n=== 测试 table 库 ===");
arr = {10, 20, 30};
print("原始数组: " .. arr[1] .. ", " .. arr[2] .. ", " .. arr[3]);
table.insert(arr, 40);
print("插入后: " .. arr[1] .. ", " .. arr[2] .. ", " .. arr[3] .. ", " .. arr[4]);
table.remove(arr, 2);
print("删除第二个: " .. arr[1] .. ", " .. arr[2] .. ", " .. arr[3]);
print("数组拼接: " .. table.concat(arr, ", "));

print("\n=== 所有测试完成 ===");