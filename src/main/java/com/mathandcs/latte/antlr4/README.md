ANTLR4解法
========

``` shell
curl -O http://www.antlr.org/download/antlr-4.7-complete.jar
alias antlr4="java -jar /usr/local/lib/antlr-4.7-complete.jar"
touch Calc.g4
antlr4 -visitor Calc.g4
```
