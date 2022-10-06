/*FANUS LUDOVIC*/
/*AMAH GNIMDOU RICHARD*/

grammar PP;

type : 'integer' | 'boolean' | array;
array : 'array of ' type;

constante  :
Const | 'true' | 'false' ;

cibleAppel  :
	'read' | 'write' | fonction ;


fonction :ID ;

expressions :
	constante 													|
	ID                                                          |
	//uop
	'-' expressions 											|
	'not' expressions											|
	//bop
	expressions ('+'|'-') expressions									|

	expressions ('and'|'or') expressions								|

	expressions ('<'|'<='|'='|'!='|'>='|'>') expressions 								|
	cibleAppel '(' listeArguments ')'		|
	expressions '[' expressions ']'							|
	'new array of ' type '[' expressions ']'	;

variables :
	('var' ID ':' type ('var' ID ':' type)* )? ;

listeDeclarationArguments :
	(ID ':' type (',' ID ':' type)*)? ;

listeArguments :
	(expressions (',' expressions)*)? ;

instructions :
   // expressions                                                 |
	ID ':' '=' expressions 										|
	expressions '[' expressions ']' ':' '=' expressions			|
	'if' expressions 'then' instructions 'else' instructions	|
	'while' expressions 'do' instructions						|
	cibleAppel '(' listeArguments ')'		                    |
	instructions ';' instructions                               |
	WS;

definitionFonctionProcedure :
	fonction '(' listeDeclarationArguments ')' (':' type)?
	variables
	instructions;

programmes :
	variables
	definitionFonctionProcedure*
	instructions;

WS : [ \t\r\n]+ -> skip ;
Const : ('0'..'9')+ ;
ID:[a-zA-Z_][a-zA-Z_0-9]*;