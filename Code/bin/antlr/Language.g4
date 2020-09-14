grammar Language;
@header {
 package antlr;
}
prog:(decl)+ EOF # Program
	;
	
decl:'var' ID TYPE # Declaration
	;

TYPE: 'Int' | 'BOOL' | 'REAL';
ID : [a-z][a-zA-Z0-9]*;
COMMENT: '//'~[\r\n]* -> skip;
WS: [\t\n]+ ->skip;	