package app;

import antlr.LanguageParser;
import expression.AntlrToProgram;
import expression.ExpressionProcessor;
import expression.MyErrorListener;
import expression.Program;
import antlr.LanguageLexer;
import antlr.LanguageParser;
import antlr.LanguageLexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.*;

public class ExpressionApp {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.print("Usage: file name\n");
		} else {
			String filename = args[0];
			LanguageParser parser = getParser(filename);

			ParseTree antlrAST = parser.prog();

			if (MyErrorListener.haserror) {

			} else {
				AntlrToProgram progVisitor = new AntlrToProgram();
				Program prog = progVisitor.visit(antlrAST);

				if (progVisitor.semanticErrors.isEmpty()) {
					ExpressionProcessor ep = new ExpressionProcessor(prog.expressions);

					File f;
					int i = 0;
					f = new File("output" + i + ".txt");

					if (!f.exists()) {
						f.createNewFile();
						PrintStream out = new PrintStream(f);
						System.setOut(out);
						for (String eval : ep.getEvaluationResults()) {
							System.out.println(eval);

							System.setOut(out);
						}
					} else {
						while (f.exists()) {
							i++;
							f = new File("output" + i + ".txt");
							if (!f.exists()) {
								f.createNewFile();
								PrintStream out = new PrintStream(f);
								System.setOut(out);
								for (String eval : ep.getEvaluationResults()) {
									System.out.println(eval);

									System.setOut(out);
								}
								break;
							}

						}
					}

				} else {
					for (String err : progVisitor.semanticErrors) {
						System.out.println(err);
					}
				}
			}

		}
	}

	private static LanguageParser getParser(String filename) {
		LanguageParser parser = null;

		try {
			CharStream input = CharStreams.fromFileName(filename);
			LanguageLexer lexer = new LanguageLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new LanguageParser(tokens);

			parser.removeErrorListeners();
			parser.addErrorListener(new MyErrorListener());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parser;
	}

}
