grammar Language;

@header {
	package antlr;
	

}


prog:(decl | assign | expr | arrayinsert | constructor | function | forloop)+ EOF # Program
	;


forloop: 'do'  NUM 'times' '{' ( expr | assign )* '}' #for_loop
	   | 'until' expr '{' ( assign |expr  )* '}' #until
		;
		
		

decl: 'var' ID TYPE # Declaration
	| 'var' ID TYPE ':=' NUM # Declaration_with_value_num
	| 'var' ID TYPE ':=' ID # Declaration_with_value_boolean
	| TYPE 'Array' ID ':=' 'new' 'Array'  #Array_Declaration 
	
	
	;
	
constructor: 'constructor' ID '(' constructor* ')'  #constructor_declaration
			|'New' ID ID #constructor_declaration_runtime
			| '(' TYPE ID ')' #constructor_parameter
			| ID '.' ID ':=' NUM #consructor_value_assign_num
			|ID '.' ID ':=' ID #consructor_value_assign_bool
			
			
			;

assign: ID ':=' NUM  # Assignment_with_num
		|ID ':=' ID  # Assignment_with_bool
	  | ID ':=' expr #Assignment_to_exprmatic_expression
	    | ID ':=' expr #until_to_expression
	  ;

arrayinsert: ID '[' NUM ']' ':=' NUM #ARRAY_insertion_with_num
			| ID '[' NUM ']' ':=' ID #ARRAY_insertion_with_bool
		;

function: TYPE ID '(' TYPE ID (',' TYPE ID)* ')' '{' expr* '}' #Function_with_para
		| TYPE ID '(' ')' '{' expr* '}' #Function_without_para
		;

funcreturn: ID '(' expr (',' expr)* ')' #Function_invocation_withParam
		  | ID '(' ')' #Function_invocation_withoutParam
		  ; 


expr: expr '*' expr #expr_multi
	| expr '+' expr #expr_add
	| expr '-' expr #expr_sub
	| expr '/' expr #expr_div
	| expr '<' expr  #boolean_less_then
	| expr '>' expr  #boolean_greater_then
	| expr '<=' expr  #boolean_less_then_equal_to
	| expr '>=' expr  #boolean_greater_then_equal_to
	| expr 'and' expr*  #boolean_and
	| expr 'or' expr*  #boolean_or
	| 'not'expr  #boolean_not
	| expr '=' expr #boolean_equal_to
	| '(' expr ')' # parentheses_expr
	| ID '[' NUM ']' #array_declaration_expr
	| ID '[' ID ']' #array_declaration_expr_id_position
	| BOOL  		# boolean_expr
	| NUM           #expr_declar_num
	| ID 			#expr_decalr_id
	| condition		#codition_expression
	| 'to_real' expr # change_to_real_num
	| 'return' expr  #expr_with_retun
	| 'return' funcreturn # function_return
	| 'require' expr 'do'  condition  'ensure' expr  #correctness_if_condition
	| 'require' expr 'do'  expr*  'ensure' expr  #correctness_assignment
	| funcreturn #funcExpr
	;
	
condition:
 		'if' expr 'then' condition 'end' #if_condition_to_condition
 		| 'if' expr 'then' condition 'else' condition 'end'  #nested_else 
 		| 'if' expr 'then' condition 'else' assign 'end'  #nested_if_else_assignment 
 		| 'if' expr 'then' assign* 'else' condition 'end'  #nested_if_assignment_else
		|'if' expr 'then' assign 'end'  #if_condition_to_assignment
		 | 'if' expr 'then' assign 'else' assign 'end'  #if_else_expr
		 | 'if' expr 'then' assign* 'end'  #if_condition_to_assignment2
	  	 | 'if' expr 'then' assign* 'else' assign* 'end' #pure_if_else
	     ;

		
	
ID : [a-z][a-zA-Z0-9_]*;
ID_two : [a-z][a-zA-Z0-9_]*;
PERM : '(' [a-z][a-zA-Z0-9_]* ')';
NUM : '0' | '-'?[0-9][0-9]* | '-'?[0-9]+[.][0-9]*;
TYPE : 'Int' | 'Bool' | 'Real';
BOOL : 'true' | 'false';
COMMENT : '//' ~[\r\n]* -> skip;
WS : [ \t\n\r]+ -> skip;