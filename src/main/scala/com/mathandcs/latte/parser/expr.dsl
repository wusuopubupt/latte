expr ::= term { + term | - term}
term ::= factor { * factor | / factor}
factor ::= floatingPointNumber | '('expr')'
