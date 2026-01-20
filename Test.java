import core.evaluator.*;
import core.exception.LexerException;
import core.exception.ParserException;
import core.lexer.Lexer;
import core.lexer.Token;
import core.parser.Parser;

/**
 * Lua 5.1 解释器测试类
 * 包含各种测试用例来验证解释器的功能
 */
public class Test {
    
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Lua 5.1 Interpreter Test Suite");
        System.out.println("========================================\n");
        
        // 测试词法分析器
        testLexer();
        
        // 测试类型系统
        testValueTypes();
        
        // 测试算术运算
        testArithmetic();
        
        // 测试比较运算
        testComparison();
        
        // 测试逻辑运算
        testLogical();
        
        // 测试字符串操作
        testString();
        
        // 测试表达式求值
        testExpressionEvaluation();
        
        // 测试语句执行
        testStatements();
        
        // 输出测试结果
        System.out.println("\n========================================");
        System.out.println("Test Results");
        System.out.println("========================================");
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("Total:  " + (passedTests + failedTests));
        System.out.println("========================================");
        
        System.exit(failedTests > 0 ? 1 : 0);
    }
    
    // ==================== 词法分析器测试 ====================
    
    private static void testLexer() {
        System.out.println("Testing Lexer...");
        
        testCase("Token recognition - numbers", () -> {
            try {
                Lexer lexer = new Lexer("123 3.14 1e10");
                var tokens = lexer.tokenize();
                assertCondition(tokens.size() == 4); // 3 numbers + EOF
                assertCondition(tokens.get(0).getType() == core.lexer.TokenType.NUMBER_LITERAL);
                assertCondition(tokens.get(1).getType() == core.lexer.TokenType.NUMBER_LITERAL);
                assertCondition(tokens.get(2).getType() == core.lexer.TokenType.NUMBER_LITERAL);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Token recognition - strings", () -> {
            try {
                Lexer lexer = new Lexer("\"hello\" 'world'");
                var tokens = lexer.tokenize();
                assertCondition(tokens.size() == 3); // 2 strings + EOF
                assertCondition(tokens.get(0).getType() == core.lexer.TokenType.STRING_LITERAL);
                assertCondition(tokens.get(1).getType() == core.lexer.TokenType.STRING_LITERAL);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Token recognition - operators", () -> {
            try {
                Lexer lexer = new Lexer("+ - * / ^ % == ~= < > <= >=");
                var tokens = lexer.tokenize();
                assertCondition(tokens.size() == 13); // 12 operators + EOF
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Token recognition - keywords", () -> {
            try {
                Lexer lexer = new Lexer("and or not if then else end while do");
                var tokens = lexer.tokenize();
                assertCondition(tokens.size() == 10); // 9 keywords + EOF
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println();
    }
    
    // ==================== 类型系统测试 ====================
    
    private static void testValueTypes() {
        System.out.println("Testing Value Types...");
        
        testCase("Nil value", () -> {
            Value nil = NilValue.INSTANCE;
            assertCondition(nil.isNil());
            assertCondition(nil.toString().equals("nil"));
        });
        
        testCase("Boolean value", () -> {
            Value bool = new BooleanValue(true);
            assertCondition(bool.isBoolean());
            assertCondition(bool.toString().equals("true"));
            assertCondition(bool.asBoolean().getValue() == true);
        });
        
        testCase("Number value", () -> {
            Value num = new NumberValue(3.14);
            assertCondition(num.isNumber());
            assertCondition(num.asNumber().getValue() == 3.14);
        });
        
        testCase("String value", () -> {
            Value str = new StringValue("hello");
            assertCondition(str.isString());
            assertCondition(str.asString().getValue().equals("hello"));
        });
        
        System.out.println();
    }
    
    // ==================== 算术运算测试 ====================
    
    private static void testArithmetic() {
        System.out.println("Testing Arithmetic Operations...");
        
        testCase("Addition", () -> {
            try {
                Value a = new NumberValue(5);
                Value b = new NumberValue(3);
                Value result = a.add(b);
                assertCondition(result.isNumber());
                assertCondition(result.asNumber().getValue() == 8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Subtraction", () -> {
            try {
                Value a = new NumberValue(10);
                Value b = new NumberValue(4);
                Value result = a.subtract(b);
                assertCondition(result.asNumber().getValue() == 6);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Multiplication", () -> {
            try {
                Value a = new NumberValue(6);
                Value b = new NumberValue(7);
                Value result = a.multiply(b);
                assertCondition(result.asNumber().getValue() == 42);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Division", () -> {
            try {
                Value a = new NumberValue(20);
                Value b = new NumberValue(4);
                Value result = a.divide(b);
                assertCondition(result.asNumber().getValue() == 5);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Modulo", () -> {
            try {
                Value a = new NumberValue(17);
                Value b = new NumberValue(5);
                Value result = a.modulo(b);
                assertCondition(result.asNumber().getValue() == 2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Power", () -> {
            try {
                Value a = new NumberValue(2);
                Value b = new NumberValue(3);
                Value result = a.power(b);
                assertCondition(result.asNumber().getValue() == 8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Negation", () -> {
            try {
                Value a = new NumberValue(5);
                Value result = a.negate();
                assertCondition(result.asNumber().getValue() == -5);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println();
    }
    
    // ==================== 比较运算测试 ====================
    
    private static void testComparison() {
        System.out.println("Testing Comparison Operations...");
        
        testCase("Equal", () -> {
            Value a = new NumberValue(5);
            Value b = new NumberValue(5);
            Value result = a.equal(b);
            assertCondition(result.isBoolean());
            assertCondition(result.asBoolean().getValue() == true);
        });
        
        testCase("Not equal", () -> {
            Value a = new NumberValue(5);
            Value b = new NumberValue(3);
            Value result = a.notEqual(b);
            assertCondition(result.asBoolean().getValue() == true);
        });
        
        testCase("Less than", () -> {
            try {
                Value a = new NumberValue(3);
                Value b = new NumberValue(5);
                Value result = a.lessThan(b);
                assertCondition(result.asBoolean().getValue() == true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Greater than", () -> {
            try {
                Value a = new NumberValue(10);
                Value b = new NumberValue(5);
                Value result = a.greaterThan(b);
                assertCondition(result.asBoolean().getValue() == true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Less or equal", () -> {
            try {
                Value a = new NumberValue(5);
                Value b = new NumberValue(5);
                Value result = a.lessEqual(b);
                assertCondition(result.asBoolean().getValue() == true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Greater or equal", () -> {
            try {
                Value a = new NumberValue(10);
                Value b = new NumberValue(5);
                Value result = a.greaterEqual(b);
                assertCondition(result.asBoolean().getValue() == true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println();
    }
    
    // ==================== 逻辑运算测试 ====================
    
    private static void testLogical() {
        System.out.println("Testing Logical Operations...");
        
        testCase("Not true", () -> {
            Value bool = new BooleanValue(true);
            Value result = bool.not();
            assertCondition(result.asBoolean().getValue() == false);
        });
        
        testCase("Not false", () -> {
            Value bool = new BooleanValue(false);
            Value result = bool.not();
            assertCondition(result.asBoolean().getValue() == true);
        });
        
        testCase("Not nil", () -> {
            Value nil = NilValue.INSTANCE;
            Value result = nil.not();
            assertCondition(result.asBoolean().getValue() == true);
        });
        
        System.out.println();
    }
    
    // ==================== 字符串操作测试 ====================
    
    private static void testString() {
        System.out.println("Testing String Operations...");
        
        testCase("String concatenation", () -> {
            try {
                Value a = new StringValue("hello");
                Value b = new StringValue(" world");
                Value result = a.concat(b);
                assertCondition(result.isString());
                assertCondition(result.asString().getValue().equals("hello world"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("String length", () -> {
            try {
                Value str = new StringValue("hello");
                Value result = str.length();
                assertCondition(result.isNumber());
                assertCondition(result.asNumber().getValue() == 5);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("String comparison", () -> {
            try {
                Value a = new StringValue("apple");
                Value b = new StringValue("banana");
                Value result = a.lessThan(b);
                assertCondition(result.asBoolean().getValue() == true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println();
    }
    
    // ==================== 表达式求值测试 ====================
    
    private static void testExpressionEvaluation() {
        System.out.println("Testing Expression Evaluation...");
        
        testCase("Simple arithmetic", () -> {
            try {
                String source = "print(2 + 3 * 4);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Parentheses", () -> {
            try {
                String source = "print((2 + 3) * 4);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Power operator", () -> {
            try {
                String source = "print(2 ^ 3);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Comparison", () -> {
            try {
                String source = "print(5 > 3);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println();
    }
    
    // ==================== 语句执行测试 ====================
    
    private static void testStatements() {
        System.out.println("Testing Statements...");
        
        testCase("Print statement", () -> {
            try {
                String source = "print(42);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Multiple statements", () -> {
            try {
                String source = "print(1); print(2); print(3);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        testCase("Complex expression", () -> {
            try {
                String source = "print((10 + 5) * 2 - 3);";
                runCode(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        System.out.println();
    }
    
    // ==================== 辅助方法 ====================
    
    private static void testCase(String name, Runnable test) {
        try {
            test.run();
            System.out.println("  ✓ " + name);
            passedTests++;
        } catch (AssertionError e) {
            System.out.println("  ✗ " + name + ": " + e.getMessage());
            failedTests++;
        } catch (Exception e) {
            System.out.println("  ✗ " + name + ": " + e.getClass().getSimpleName() + " - " + e.getMessage());
            failedTests++;
        }
    }
    
    private static void runCode(String source) throws Exception {
        Lexer lexer = new Lexer(source);
        var tokens = lexer.tokenize();
        Parser parser = new Parser(tokens);
        var statements = parser.parse();
        Evaluator evaluator = new Evaluator();
        evaluator.interpret(statements);
    }
    
    private static void assertCondition(boolean cond) {
        if (!cond) {
            throw new AssertionError("Assertion failed");
        }
    }
    
    private static void assertEquals(Object a, Object b) {
        if (!a.equals(b)) {
            throw new AssertionError("Expected " + b + " but got " + a);
        }
    }
}