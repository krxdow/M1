//AMAH Gnimdou Richard
//Fanus Ludovic

grammar PP;

programmes returns [PPProg p] :
   	(par =listdeclaration {$p.globals = $par.variables;})?
   	(def = definition {$p.defs.add($def.d);})*
   	i=instructions {$p.code=$i.instr;};

// listdeclaration : 'var' (pair)+;
listdeclaration  returns [ArrayList<Pair<String,Type>> variables]
@init{$variables= new ArrayList<Pair<String,Type>>();}:
'var'(v=pairvar {$variables.add($v.pair);})+;

//pair : variables ':' type ;
pairvar returns [Pair<String,Type> pair]
//@init{$pair= new Pair<String,Type>("",null);}:
@init{$pair= new Pair<String,Type>(null,null);}:
s = variable {$pair.left= $s.var.name;} ':' t =type{$pair.right=$t.T;};

// definition: ID '(' pair ')' (':' type) =>>> f ((x : τ )∗ ) [: τ ]
// listdeclaration=>>> [var (x : τ )+ ]
definition returns [PPDef d] @init {$d = new PPProc(null, null, null, null);}:
name = ID {$d.name=$name.text;}
'('(arg=pairvar {$d.args.add($arg.pair);})')'
(':' t =type {$d = new PPFun($d.name, $d.args, null, null, $t.T);})?
(locvar = listdeclaration{$d.locals=$locvar.variables;})?
inst = instructions {$d.code=$inst.instr;};

//instructions: assignment | arrayAssignment | condition | loop | procedure | instructions ';' instructions | WS;
instructions returns [PPInst instr]:
   	a=assignment {$instr = $a.a;}			              |
   	array = arrayAssignment {$instr = $array.arraya;}   |
    c = conditionEntry	{$instr = $c.condition;}            |
    l = loop {$instr = $l.while;}                        |
   	proc = procedure {$instr = $proc.p;}                 |
   	instr1 = instructions {$instr= new PPSeq($instr1.instr, null);} ';' instr2 = instructions {((PPSeq)$instr).i2= $instr2.instr;}   |
   	skip = WS {$instr = new PPSkip();};


//assignment: varName Assign expressions;
assignment returns [PPAssign a] @init{$a=new PPAssign("", null);}:
 v= variable {$a.name=$v.var.name;}
 Assign e=expressions {$a.val=$e.expr;};

 // variable: ID
 variable returns [PPVar var]:
 name = ID {$var = new PPVar($name.text);};

//myTab[i]:=5
//arrayAssignment: expression '[' expression ']' Assign expression;
arrayAssignment returns [PPArraySet arraya] @init{$arraya= new PPArraySet(null, null, null);}:
e1 =expressions {$arraya.arr=$e1.expr;}
'['e2 =expressions {$arraya.index=$e2.expr;}']' Assign
e3 =expressions {$arraya.val=$e3.expr;} ;



expressions returns [PPExpr expr] :
    cte = constante {$expr = $cte.Cte;}
   	| name = ID {$expr = new PPVar($name.text);}
   	//uop
   	| '-'e = expressions {$expr = new PPInv($e.expr);}
   	| Not e = expressions {$expr = new PPNot($e.expr);}
   	//bop
    | e1 = expressions {$expr = new PPAdd($e1.expr, null);} BinPLUS e2 = expressions {((PPAdd)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPSub($e1.expr, null);} BinMINUS e2 = expressions {((PPSub)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPMul($e1.expr, null);} BinMUL e2 = expressions {((PPMul)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPDiv($e1.expr, null);} BinDIV e2 = expressions {((PPDiv)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPAnd($e1.expr, null);} BinAND e2 = expressions {((PPAnd)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPOr($e1.expr, null);} BinOR e2 = expressions {((PPOr)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPLt($e1.expr, null);} Lt e2 = expressions {((PPLt)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPLe($e1.expr, null);} LEq e2 = expressions {((PPLe)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPEq($e1.expr, null);} Eq e2 = expressions {((PPEq)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPNe($e1.expr, null);} NEq e2 = expressions {((PPNe)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPGe($e1.expr, null);} GEq e2 = expressions {((PPGe)$expr).e2 =  $e2.expr;}
    | e1 = expressions {$expr = new PPGt($e1.expr, null);} Gt e2 = expressions {((PPGt)$expr).e2 =  $e2.expr;}
    | f = fonction {$expr = $f.f;}
   	| arrayExpr = expressions {$expr= new PPArrayGet($arrayExpr.expr, null);}
   	'[' nthExpr = expressions {((PPArrayGet)$expr).index = $nthExpr.expr;} ']'
   	| New Array t = type {$expr= new PPArrayAlloc($t.T, null);}
   	'[' size = expressions {((PPArrayAlloc)$expr).size = $size.expr;} ']'	;

// appel: 'read' | 'write' | ID
appel returns [Callee c]  : // cible
   	'read' {$c=new Read();}
   	| 'write' {$c=new Write();}
   	|  v=ID {$c= new User($v.text);}; //pas sur

// fonction: appel '(' (expression)* ')';
fonction returns [PPFunCall f] @init{$f= new PPFunCall(null, null);}:
   a =appel {$f.callee=$a.c;} '('(e = expressions {$f.args.add($e.expr);})*')';
   //cibleAppel {$e = new Calle();} ;

// procedure: appel '(' (expression)* ')';
procedure returns [PPProcCall p] @init{$p= new PPProcCall(null, null);}:
        a =appel {$p.callee=$a.c;} '(' (e = expressions {$p.args.add($e.expr);})* ')';


//condition: 'if' expressions 'then' instructions 'else' instructions
conditionEntry returns [PPCond condition] @init {$condition = new PPCond(null, null, null);}:
   'if' e = expressions {$condition.cond = $e.expr; }
    'then' ifInstr = instructions { $condition.i1 = $ifInstr.instr ;  }
    'else' elseInstr = instructions { $condition.i2 = $elseInstr.instr ;  };


//loop: 'while' expressions 'do' instructions;
loop returns [PPWhile while] @init {$while = new PPWhile(null, null);}:
'while' e = expressions {$while.cond = $e.expr;}
 'do' whileInstr = instructions {$while.i = $whileInstr.instr;};



//type :
type returns [Type T]:
   'boolean' {$T=new Bool();} |
   'integer'  {$T = new Int();}|
   Array t=type{$T= new Array($t.T);};

constante returns [PPExpr Cte] :
   c = Const {$Cte = new PPCte(Integer.parseInt($c.text));}|
   'true' {$Cte = new PPTrue();}|
   'false' {$Cte = new PPFalse();}; // <==== To be checked



New: 'new';
Array: 'array of ';
Not: 'not';
BinPLUS: '+';
BinMINUS: '-';
BinMUL: '*';
BinDIV: '/';
BinAND: 'and';
BinOR: 'or' ;
Lt:'<';
LEq:'<='|'≤';
Gt:'>';
GEq:'>='|'⩾';
Eq:'=';
NEq:'!='|'≠';
Assign: ':=';
Const : ('0'..'9')+ ;
ID:[a-zA-Z_][a-zA-Z_0-9]*;
WS : [ \t\r\n] -> skip ;
