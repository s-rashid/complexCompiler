// Generated from Language.g4 by ANTLR 4.8

 package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LanguageParser}.
 */
public interface LanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Program}
	 * labeled alternative in {@link LanguageParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link LanguageParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(LanguageParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(LanguageParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Declaration_with_value_num}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_with_value_num(LanguageParser.Declaration_with_value_numContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Declaration_with_value_num}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_with_value_num(LanguageParser.Declaration_with_value_numContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Declaration_with_value_boolean}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_with_value_boolean(LanguageParser.Declaration_with_value_booleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Declaration_with_value_boolean}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_with_value_boolean(LanguageParser.Declaration_with_value_booleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment_with_num}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_with_num(LanguageParser.Assignment_with_numContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment_with_num}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_with_num(LanguageParser.Assignment_with_numContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment_with_bool}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_with_bool(LanguageParser.Assignment_with_boolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment_with_bool}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_with_bool(LanguageParser.Assignment_with_boolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assignment_to_exprmatic_expression}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_to_exprmatic_expression(LanguageParser.Assignment_to_exprmatic_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assignment_to_exprmatic_expression}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_to_exprmatic_expression(LanguageParser.Assignment_to_exprmatic_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_sub}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_sub(LanguageParser.Expr_subContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_sub}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_sub(LanguageParser.Expr_subContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentheses_expr}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParentheses_expr(LanguageParser.Parentheses_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentheses_expr}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParentheses_expr(LanguageParser.Parentheses_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_add}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_add(LanguageParser.Expr_addContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_add}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_add(LanguageParser.Expr_addContext ctx);
	/**
	 * Enter a parse tree produced by the {@code codition_expression}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCodition_expression(LanguageParser.Codition_expressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code codition_expression}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCodition_expression(LanguageParser.Codition_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_multi}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_multi(LanguageParser.Expr_multiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_multi}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_multi(LanguageParser.Expr_multiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_and}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_and(LanguageParser.Boolean_andContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_and}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_and(LanguageParser.Boolean_andContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_with_retun}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_with_retun(LanguageParser.Expr_with_retunContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_with_retun}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_with_retun(LanguageParser.Expr_with_retunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_greater_then_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_greater_then_equal_to(LanguageParser.Boolean_greater_then_equal_toContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_greater_then_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_greater_then_equal_to(LanguageParser.Boolean_greater_then_equal_toContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_or}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_or(LanguageParser.Boolean_orContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_or}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_or(LanguageParser.Boolean_orContext ctx);
	/**
	 * Enter a parse tree produced by the {@code change_to_real_num}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterChange_to_real_num(LanguageParser.Change_to_real_numContext ctx);
	/**
	 * Exit a parse tree produced by the {@code change_to_real_num}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitChange_to_real_num(LanguageParser.Change_to_real_numContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_decalr_id}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_decalr_id(LanguageParser.Expr_decalr_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_decalr_id}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_decalr_id(LanguageParser.Expr_decalr_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_greater_then}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_greater_then(LanguageParser.Boolean_greater_thenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_greater_then}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_greater_then(LanguageParser.Boolean_greater_thenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_less_then_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_less_then_equal_to(LanguageParser.Boolean_less_then_equal_toContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_less_then_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_less_then_equal_to(LanguageParser.Boolean_less_then_equal_toContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_expr}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expr(LanguageParser.Boolean_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_expr}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expr(LanguageParser.Boolean_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_not}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_not(LanguageParser.Boolean_notContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_not}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_not(LanguageParser.Boolean_notContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_equal_to(LanguageParser.Boolean_equal_toContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_equal_to(LanguageParser.Boolean_equal_toContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean_less_then}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_less_then(LanguageParser.Boolean_less_thenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean_less_then}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_less_then(LanguageParser.Boolean_less_thenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_declar_num}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_declar_num(LanguageParser.Expr_declar_numContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_declar_num}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_declar_num(LanguageParser.Expr_declar_numContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_condition_to_assignment}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterIf_condition_to_assignment(LanguageParser.If_condition_to_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_condition_to_assignment}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitIf_condition_to_assignment(LanguageParser.If_condition_to_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_else_expr}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_expr(LanguageParser.If_else_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_else_expr}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_expr(LanguageParser.If_else_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nested_else}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterNested_else(LanguageParser.Nested_elseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nested_else}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitNested_else(LanguageParser.Nested_elseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_condition_to_assignment2}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterIf_condition_to_assignment2(LanguageParser.If_condition_to_assignment2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code if_condition_to_assignment2}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitIf_condition_to_assignment2(LanguageParser.If_condition_to_assignment2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code if_condition_to_condition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterIf_condition_to_condition(LanguageParser.If_condition_to_conditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_condition_to_condition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitIf_condition_to_condition(LanguageParser.If_condition_to_conditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pure_if_else}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterPure_if_else(LanguageParser.Pure_if_elseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pure_if_else}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitPure_if_else(LanguageParser.Pure_if_elseContext ctx);
}