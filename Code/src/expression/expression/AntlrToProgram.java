package expression;

import java.util.ArrayList;
import java.util.List;

import antlr.LanguageBaseVisitor;
import antlr.LanguageParser.ProgramContext;

public class AntlrToProgram extends LanguageBaseVisitor<Program> {

	
	public List<String> semanticErrors;
	@Override
	public Program visitProgram(ProgramContext ctx) {
		Program prog = new Program();
		
		semanticErrors = new ArrayList<>();
		
		AntlrToExpression exprVistor =  new  AntlrToExpression (semanticErrors) ;
		
		for(int i = 0; i< ctx.getChildCount(); i++) {
			if(i == ctx.getChildCount() -1) {
				
			}else {
				prog.addExpression(exprVistor.visit(ctx.getChild(i))) ;
			}
		}
		
		return prog;
	}

}
