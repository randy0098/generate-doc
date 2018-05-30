@ECHO OFF
IF NOT "%~f0" == "~f0" GOTO :WinNT
@"java -cp ;D:\Work\MyTest\asciidoctor-maven-examples\asciidoctor-pdf-cjk-example\target\test-classes;D:\Work\MyTest\asciidoctor-maven-examples\asciidoctor-pdf-cjk-example\target\classes;C:\Users\Randy\.m2\repository\org\jruby\jruby-complete\9.1.8.0\jruby-complete-9.1.8.0.jar org.jruby.Main" "D:/Work/MyTest/asciidoctor-maven-examples/asciidoctor-pdf-cjk-example/target/gems/bin/asciidoctor-pdf-cjk-kai_gen_gothic-install" %1 %2 %3 %4 %5 %6 %7 %8 %9
GOTO :EOF
:WinNT
@"java -cp ;D:\Work\MyTest\asciidoctor-maven-examples\asciidoctor-pdf-cjk-example\target\test-classes;D:\Work\MyTest\asciidoctor-maven-examples\asciidoctor-pdf-cjk-example\target\classes;C:\Users\Randy\.m2\repository\org\jruby\jruby-complete\9.1.8.0\jruby-complete-9.1.8.0.jar org.jruby.Main" "%~dpn0" %*
