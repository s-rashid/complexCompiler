// Generated from Language.g4 by ANTLR 4.8

 package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link LanguageParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(LanguageParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Declaration_with_value_num}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_with_value_num(LanguageParser.Declaration_with_value_numContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Declaration_with_value_boolean}
	 * labeled alternative in {@link LanguageParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_with_value_boolean(LanguageParser.Declaration_with_value_booleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment_with_num}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_with_num(LanguageParser.Assignment_with_numContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment_with_bool}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_with_bool(LanguageParser.Assignment_with_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assignment_to_exprmatic_expression}
	 * labeled alternative in {@link LanguageParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_to_exprmatic_expression(LanguageParser.Assignment_to_exprmatic_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_sub}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_sub(LanguageParser.Expr_subContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentheses_expr}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses_expr(LanguageParser.Parentheses_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_add}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_add(LanguageParser.Expr_addContext ctx);
	/**
	 * Visit a parse tree produced by the {@code codition_expression}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodition_expression(LanguageParser.Codition_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_multi}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_multi(LanguageParser.Expr_multiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_and}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_and(LanguageParser.Boolean_andContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_with_retun}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_with_retun(LanguageParser.Expr_with_retunContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_greater_then_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_greater_then_equal_to(LanguageParser.Boolean_greater_then_equal_toContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_or}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_or(LanguageParser.Boolean_orContext ctx);
	/**
	 * Visit a parse tree produced by the {@code change_to_real_num}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChange_to_real_num(LanguageParser.Change_to_real_numContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_decalr_id}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_decalr_id(LanguageParser.Expr_decalr_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_greater_then}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_greater_then(LanguageParser.Boolean_greater_thenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_less_then_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_less_then_equal_to(LanguageParser.Boolean_less_then_equal_toContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_expr}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_expr(LanguageParser.Boolean_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_not}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_not(LanguageParser.Boolean_notContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_equal_to}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_equal_to(LanguageParser.Boolean_equal_toContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean_less_then}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_less_then(LanguageParser.Boolean_less_thenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_declar_num}
	 * labeled alternative in {@link LanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_declar_num(LanguageParser.Expr_declar_numContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_condition_to_assignment}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_condition_to_assignment(LanguageParser.If_condition_to_assignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_else_expr}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else_expr(LanguageParser.If_else_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nested_else}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNested_else(LanguageParser.Nested_elseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_condition_to_assignment2}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_condition_to_assignment2(LanguageParser.If_condition_to_assignment2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code if_condition_to_condition}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_condition_to_condition(LanguageParser.If_condition_to_conditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pure_if_else}
	 * labeled alternative in {@link LanguageParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPure_if_else(LanguageParser.Pure_if_elseContext ctx);
}