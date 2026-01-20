import core.evaluator.Evaluator;
import core.exception.LexerException;
import core.exception.ParserException;
import core.lexer.Lexer;
import core.parser.Parser;
import core.optimizer.ConstantFolder;
import core.macro.builtin.BuiltinMacros;
import core.macro.MacroExpander;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Lua 5.1 解释器主入口
 * 提供命令行接口来执行 Lua 脚本
 */
public class Home {
    
    public static void main(String[] args) {
        // 初始化宏系统
        BuiltinMacros.registerAll();
        
        if (args.length == 0) {
            runRepl();
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            System.out.println("Usage: java Home [script.lua]");
            System.out.println("  If no script is provided, starts in REPL mode");
            System.exit(64);
        }
    }
    
    /**
     * 执行 Lua 脚本文件
     */
    private static void runFile(String path) {
        try {
            String source = Files.readString(Paths.get(path));
            run(source);
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(74);
        }
    }
    
    /**
     * 运行 REPL（交互式解释器）
     */
    private static void runRepl() {
        System.out.println("Lua 5.1 Interpreter (Java Implementation)");
        System.out.println("Type 'exit()' or press Ctrl+C to exit");
        System.out.println();
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            
            String line = scanner.nextLine().trim();
            
            if (line.equals("exit()") || line.equals("exit")) {
                break;
            }
            
            if (line.isEmpty()) {
                continue;
            }
            
            run(line);
        }
        
        scanner.close();
        System.out.println("\nGoodbye!");
    }
    
    /**
     * 运行 Lua 源代码
     */
    private static void run(String source) {
        try {
            // 词法分析
            Lexer lexer = new Lexer(source);
            var tokens = lexer.tokenize();
            
            // 语法分析
            Parser parser = new Parser(tokens);
            var statements = parser.parse();
            
            // 宏展开
            MacroExpander expander = new MacroExpander();
            var expandedStatements = expander.expandStatements(statements);
            
            // 常量折叠优化
            ConstantFolder optimizer = new ConstantFolder();
            var optimizedStatements = optimizer.optimize(expandedStatements);
            
            // 求值执行
            Evaluator evaluator = new Evaluator();
            
            // 加载标准库
            std.LibraryRegistry.getInstance().loadAll(evaluator.getEnvironment());
            
            evaluator.interpret(optimizedStatements);
            
        } catch (LexerException e) {
            System.err.println(e.getMessage());
        } catch (ParserException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Runtime error: " + e.getMessage());
        }
    }
}