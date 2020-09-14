package expression;

import java.util.ArrayList;
import java.util.List;

import antlr.LanguageBaseVisitor;
import antlr.LanguageParser;
import antlr.LanguageParser.Expr_addContext;
import antlr.LanguageParser.Expr_decalr_idContext;
import antlr.LanguageParser.Expr_declar_numContext;
import antlr.LanguageParser.Expr_divContext;
import antlr.LanguageParser.Expr_multiContext;

import antlr.LanguageParser.Expr_subContext;
import antlr.LanguageParser.Expr_with_retunContext;
import antlr.LanguageParser.For_loopContext;
import antlr.LanguageParser.If_condition_to_assignment2Context;
import antlr.LanguageParser.ARRAY_insertion_with_boolContext;
import antlr.LanguageParser.ARRAY_insertion_with_numContext;
import antlr.LanguageParser.Array_DeclarationContext;
import antlr.LanguageParser.Array_declaration_exprContext;
import antlr.LanguageParser.Array_declaration_expr_id_positionContext;
import antlr.LanguageParser.Assignment_to_exprmatic_expressionContext;
import antlr.LanguageParser.Assignment_with_boolContext;
import antlr.LanguageParser.Assignment_with_numContext;
import antlr.LanguageParser.Boolean_andContext;
import antlr.LanguageParser.Boolean_equal_toContext;
import antlr.LanguageParser.Boolean_greater_thenContext;
import antlr.LanguageParser.Boolean_greater_then_equal_toContext;
import antlr.LanguageParser.Boolean_less_thenContext;
import antlr.LanguageParser.Boolean_less_then_equal_toContext;
import antlr.LanguageParser.Boolean_notContext;
import antlr.LanguageParser.Boolean_orContext;
import antlr.LanguageParser.Change_to_real_numContext;
import antlr.LanguageParser.Consructor_value_assign_boolContext;
import antlr.LanguageParser.Consructor_value_assign_numContext;
import antlr.LanguageParser.Constructor_declarationContext;
import antlr.LanguageParser.Constructor_declaration_runtimeContext;
import antlr.LanguageParser.Constructor_parameterContext;
import antlr.LanguageParser.Correctness_assignmentContext;
import antlr.LanguageParser.Correctness_if_conditionContext;
import antlr.LanguageParser.DeclarationContext;
import antlr.LanguageParser.Declaration_with_value_booleanContext;
import antlr.LanguageParser.Declaration_with_value_numContext;
import antlr.LanguageParser.If_condition_to_assignmentContext;
import antlr.LanguageParser.If_condition_to_conditionContext;
import antlr.LanguageParser.If_else_exprContext;
import antlr.LanguageParser.Nested_elseContext;
import antlr.LanguageParser.Nested_if_assignment_elseContext;
import antlr.LanguageParser.Nested_if_else_assignmentContext;
import antlr.LanguageParser.Parentheses_exprContext;
import antlr.LanguageParser.ProgramContext;
import antlr.LanguageParser.Pure_if_elseContext;
import antlr.LanguageParser.UntilContext;
import antlr.LanguageParser.Until_to_expressionContext;

public class AntlrToExpression extends LanguageBaseVisitor<Expression> {

	private List<String> vars;

	private List<String> semanticErrors;

	public AntlrToExpression(List<String> semanticErrors) {
		this.vars = new ArrayList<>();
		this.semanticErrors = semanticErrors;
	}

	@Override
	public Expression visitArray_declaration_expr_id_position(Array_declaration_expr_id_positionContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		String pos_Declaration = ctx.getChild(2).getText();
		return new array_declaration_expr_id_position(id_Declaration, pos_Declaration);
	}

	@Override
	public Expression visitArray_declaration_expr(Array_declaration_exprContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		String pos_Declaration = ctx.getChild(2).getText();
		return new array_declaration_expr(id_Declaration, pos_Declaration);
	}

	@Override
	public Expression visitUntil_to_expression(Until_to_expressionContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		Expression right = visit(ctx.getChild(2));

		return new until_to_expression(id_Declaration, right);
	}

	@Override
	public Expression visitUntil(UntilContext ctx) {
		// TODO Auto-generated method stub

		Expression condition = visit(ctx.getChild(1));
		int i = 3;
		ArrayList<Expression> body = new ArrayList<Expression>();
		while (!ctx.getChild(i).getText().equals("}")) {
			body.add(visit(ctx.getChild(i)));
			i += 1;
		}
		return new Until(condition, body);
	}

	public Expression visitFor_loop(For_loopContext ctx) {
		// TODO Auto-generated method stub
		String counter = ctx.getChild(1).getText();
		int i = 4;
		ArrayList<Expression> body = new ArrayList<Expression>();
		while (!ctx.getChild(i).getText().equals("}")) {
			body.add(visit(ctx.getChild(i)));
			i += 1;
		}
		return new For_loop(counter, body);
	}

	@Override
	public Expression visitNested_if_assignment_else(Nested_if_assignment_elseContext ctx) {
		// TODO Auto-generated method stub
		Expression if_condition = visit(ctx.getChild(1));

		int i = 3;
		ArrayList<Expression> case_true = new ArrayList<Expression>();

		while (!ctx.getChild(i).getText().equals("else")) {
			case_true.add(visit(ctx.getChild(i)));
			i += 1;
		}
		Expression else_condition = visit(ctx.getChild(i + 1));
		return new nested_if_assignment_else(if_condition, case_true, else_condition);
	}

	@Override
	public Expression visitNested_if_else_assignment(Nested_if_else_assignmentContext ctx) {
		// TODO Auto-generated method stub
		Expression if_condition = visit(ctx.getChild(1));
		Expression true_condition = visit(ctx.getChild(3));
		Expression else_condition = visit(ctx.getChild(5));

		return new nested_if_else_assignment(if_condition, true_condition, else_condition);
	}

	@Override
	public Expression visitNested_else(Nested_elseContext ctx) {
		// TODO Auto-generated method stub
		Expression if_condition = visit(ctx.getChild(1));
		Expression true_condition = visit(ctx.getChild(3));
		Expression else_condition = visit(ctx.getChild(5));

		return new nested_else(if_condition, true_condition, else_condition);
	}

	@Override
	public Expression visitIf_condition_to_condition(If_condition_to_conditionContext ctx) {
		Expression if_condition = visit(ctx.getChild(1));
		Expression true_condition = visit(ctx.getChild(3));

		return new if_condition_to_condition(if_condition, true_condition);
	}

	@Override
	public Expression visitExpr_div(Expr_divContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Arth_div(left, right);
	}

	@Override
	public Expression visitCorrectness_if_condition(Correctness_if_conditionContext ctx) {
		// TODO Auto-generated method stub
		Expression pre = visit(ctx.getChild(1));

		Expression imp = visit(ctx.getChild(3));

		Expression post = visit(ctx.getChild(5));

		return new correctness_if_condition(pre, imp, post);
	}

	@Override
	public Expression visitCorrectness_assignment(Correctness_assignmentContext ctx) {
		// TODO Auto-generated method stub
		Expression pre = visit(ctx.getChild(1));

		Expression imp = visit(ctx.getChild(3));

		Expression post = visit(ctx.getChild(5));

		return new correctness_assignment(pre, imp, post);
	}

	@Override
	public Expression visitConstructor_declaration_runtime(Constructor_declaration_runtimeContext ctx) {
		// TODO Auto-generated method stub
		String id = ctx.getChild(1).getText();

		String id2 = ctx.getChild(2).getText();
		return new constructor_declaration_runtime(id, id2);
	}

	@Override
	public Expression visitConsructor_value_assign_num(Consructor_value_assign_numContext ctx) {
		// TODO Auto-generated method stub
		String id = ctx.getChild(0).getText();
		String parameter = ctx.getChild(2).getText();
		String value = ctx.getChild(4).getText();
		return new consructor_value_assign_num(id, parameter, value);
	}

	@Override
	public Expression visitConsructor_value_assign_bool(Consructor_value_assign_boolContext ctx) {
		// TODO Auto-generated method stub
		String id = ctx.getChild(0).getText();
		String parameter = ctx.getChild(2).getText();
		String value = ctx.getChild(4).getText();
		return new consructor_value_assign_bool(id, parameter, value);
	}

	@Override
	public Expression visitConstructor_parameter(Constructor_parameterContext ctx) {
		// TODO Auto-generated method stub
		String id = ctx.getChild(1).getText();
		String value = ctx.getChild(2).getText();
		return new constructor_parameter(id, value);
	}

	@Override
	public Expression visitConstructor_declaration(Constructor_declarationContext ctx) {
		// TODO Auto-generated method stub
		String id = ctx.getChild(1).getText();

		int i = 3;
		ArrayList<Expression> parameters = new ArrayList<Expression>();
		while (!ctx.getChild(i).getText().equals(")")) {

			parameters.add(visit(ctx.getChild(i)));
			i += 1;
		}

		return new constructor_declaration(id, parameters);
	}

	@Override
	public Expression visitARRAY_insertion_with_bool(ARRAY_insertion_with_boolContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		String pos_Declaration = ctx.getChild(2).getText();
		String value_Declaration = ctx.getChild(5).getText();

		return new ARRAY_insertion_with_bool(id_Declaration, pos_Declaration, value_Declaration);
	}

	@Override
	public Expression visitARRAY_insertion_with_num(ARRAY_insertion_with_numContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		String pos_Declaration = ctx.getChild(2).getText();
		String value_Declaration = ctx.getChild(5).getText();

		return new ARRAY_insertion_with_num(id_Declaration, pos_Declaration, value_Declaration);
	}

	@Override
	public Expression visitArray_Declaration(Array_DeclarationContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(2).getText();
		String type_Declaration = ctx.getChild(0).getText();

		return new Array_Declaration(id_Declaration, type_Declaration);
	}

	@Override
	public Expression visitAssignment_with_bool(Assignment_with_boolContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		String value_Declaration = ctx.getChild(2).getText();

		return new Variable_Assignment(id_Declaration, value_Declaration);
	}

	@Override
	public Expression visitDeclaration(DeclarationContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(1).getText();
		String type_Declaration = ctx.getChild(2).getText();

		return new Variable_Declaration_no_type(id_Declaration, type_Declaration);
	}

	@Override
	public Expression visitAssignment_with_num(Assignment_with_numContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		String value_Declaration = ctx.getChild(2).getText();

		return new Variable_Assignment(id_Declaration, value_Declaration);
	}

	@Override
	public Expression visitExpr_multi(Expr_multiContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Arth_multi(left, right);
	}

	@Override
	public Expression visitExpr_add(Expr_addContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Arth_add(left, right);
	}

	@Override
	public Expression visitDeclaration_with_value_num(Declaration_with_value_numContext ctx) {
		String id_Declaration = ctx.getChild(1).getText();
		String type_Declaration = ctx.getChild(2).getText();
		String value_Declaration = ctx.getChild(4).getText();

		return new Variable_Declaration_with_value(id_Declaration, type_Declaration, value_Declaration);
	}

	@Override
	public Expression visitIf_condition_to_assignment(If_condition_to_assignmentContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(1));

		Expression right = visit(ctx.getChild(3));
		return new if_condition_to_assignment(left, right);
	}

	@Override
	public Expression visitDeclaration_with_value_boolean(Declaration_with_value_booleanContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(1).getText();
		String type_Declaration = ctx.getChild(2).getText();
		String value_Declaration = ctx.getChild(4).getText();

		return new Variable_Declaration_with_value(id_Declaration, type_Declaration, value_Declaration);
	}

	@Override
	public Expression visitBoolean_greater_then(Boolean_greater_thenContext ctx) {
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_greater(left, right);
	}

	@Override
	public Expression visitBoolean_less_then_equal_to(Boolean_less_then_equal_toContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_less_then_or_equal_to(left, right);
	}

	@Override
	public Expression visitBoolean_and(Boolean_andContext ctx) {
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_and(left, right);
	}

	@Override
	public Expression visitBoolean_not(Boolean_notContext ctx) {
		// TODO Auto-generated method stub
		Expression right = visit(ctx.getChild(1));

		return new Bool_not(right);
	}

	@Override
	public Expression visitIf_else_expr(If_else_exprContext ctx) {
		Expression if_condition = visit(ctx.getChild(1));
		Expression true_condition = visit(ctx.getChild(3));
		Expression else_condition = visit(ctx.getChild(5));

		return new if_else_expr(if_condition, true_condition, else_condition);
	}

	@Override
	public Expression visitIf_condition_to_assignment2(If_condition_to_assignment2Context ctx) {
		// TODO Auto-generated method stub
		Expression condition = visit(ctx.getChild(1));
		int i = 3;
		ArrayList<Expression> case_true = new ArrayList<Expression>();
		while (!ctx.getChild(i).getText().equals("end")) {
			case_true.add(visit(ctx.getChild(i)));
			i += 1;
		}

		return new if_condition_to_assignment2(condition, case_true);
	}

	@Override
	public Expression visitPure_if_else(Pure_if_elseContext ctx) {
		// TODO Auto-generated method stub
		Expression condition = visit(ctx.getChild(1));
		int i = 3;
		ArrayList<Expression> case_true = new ArrayList<Expression>();
		ArrayList<Expression> else_condition = new ArrayList<Expression>();
		while (!ctx.getChild(i).getText().equals("else")) {
			case_true.add(visit(ctx.getChild(i)));
			i += 1;
		}
		i++;
		while (!ctx.getChild(i).getText().equals("end")) {
			else_condition.add(visit(ctx.getChild(i)));
			i += 1;
		}

		return new Pure_if_else(condition, case_true, else_condition);
	}

	@Override
	public Expression visitBoolean_equal_to(Boolean_equal_toContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_equal(left, right);
	}

	@Override
	public Expression visitBoolean_greater_then_equal_to(Boolean_greater_then_equal_toContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_greater_then_or_equal_to(left, right);
	}

	@Override
	public Expression visitBoolean_or(Boolean_orContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_or(left, right);
	}

	@Override
	public Expression visitBoolean_less_then(Boolean_less_thenContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Bool_less_then(left, right);
	}

	@Override
	public Expression visitExpr_sub(Expr_subContext ctx) {
		// TODO Auto-generated method stub
		Expression left = visit(ctx.getChild(0));

		Expression right = visit(ctx.getChild(2));

		return new Arth_sub(left, right);
	}

	@Override
	public Expression visitExpr_decalr_id(Expr_decalr_idContext ctx) {
		String num = ctx.getChild(0).getText();
		return new Variable(num);
	}

	@Override
	public Expression visitExpr_declar_num(Expr_declar_numContext ctx) {
		// TODO Auto-generated method stub
		String num = ctx.getChild(0).getText();
		return new Number(num);
	}

	@Override
	public Expression visitAssignment_to_exprmatic_expression(Assignment_to_exprmatic_expressionContext ctx) {
		// TODO Auto-generated method stub
		String id_Declaration = ctx.getChild(0).getText();
		Expression right = visit(ctx.getChild(2));

		return new Assign_to_arth(id_Declaration, right);

	}

	@Override
	public Expression visitParentheses_expr(Parentheses_exprContext ctx) {
		// TODO Auto-generated method stub
		Expression right = visit(ctx.getChild(1));

		return new parentheses_expr(right);
	}

	@Override
	public Expression visitChange_to_real_num(Change_to_real_numContext ctx) {
		// TODO Auto-generated method stub

		Expression right = visit(ctx.getChild(1));

		return new change_to_real_num(right);
	}

	@Override
	public Expression visitExpr_with_retun(Expr_with_retunContext ctx) {
		// TODO Auto-generated method stub

		return new return_class();
	}

	@Override
	public Expression visitFunction_with_para(LanguageParser.Function_with_paraContext ctx) {
		List<String> paramId = new ArrayList<String>();
		List<String> paramVal = new ArrayList<String>();
		String returnType = ctx.getChild(0).getText();
		String id = ctx.getChild(1).getText();
		int i = 3;
		int j = 4;
		boolean para = true;
		while (true) {
			// System.out.println(ctx.getChild(i - 1).getText());
			if (ctx.getChild(i - 1).getText().contentEquals(")")) {
				// para = false;
				break;
			}
			paramId.add(ctx.getChild(i).getText());
			paramVal.add(ctx.getChild(j).getText());
			i += 3;
			j += 3;
		}

		int k = i + 1;
		ArrayList<Expression> statements = new ArrayList<Expression>();
		while (!ctx.getChild(k).getText().equals("}")) {

			statements.add(visit(ctx.getChild(k)));
			k += 1;
		}

		return new DeclFuncWithParam(returnType, id, paramId, paramVal, statements);
	}

	@Override
	public Expression visitFunction_without_para(LanguageParser.Function_without_paraContext ctx) {
		String returnType = ctx.getChild(0).getText();
		String id = ctx.getChild(1).getText();

		int k = 5;
		ArrayList<Expression> statements = new ArrayList<Expression>();
		while (!ctx.getChild(k).getText().equals("}")) {

			statements.add(visit(ctx.getChild(k)));
			k += 1;
		}
		return new DeclFuncWithoutParam(returnType, id, statements);
	}

	@Override
	public Expression visitFunction_invocation_withParam(LanguageParser.Function_invocation_withParamContext ctx) {
		String funcName = ctx.getChild(0).getText();
		List<Expression> exprParam = new ArrayList<Expression>();
		int i = 2;

		while (true) {
			if (ctx.getChild(i - 1).getText().contentEquals(")")) {
				break;
			}
			exprParam.add(visit(ctx.getChild(i)));
			i += 2;
		}
		return new FunctionInvocWithPrama(funcName, exprParam);
	}

	@Override
	public Expression visitFunction_invocation_withoutParam(
			LanguageParser.Function_invocation_withoutParamContext ctx) {
		String funcName = ctx.getChild(0).getText();
		return new FuncInvocWithoutParam(funcName);
	}

	@Override
	public Expression visitFunction_return(LanguageParser.Function_returnContext ctx) {
		return visitChildren(ctx);
	}

}