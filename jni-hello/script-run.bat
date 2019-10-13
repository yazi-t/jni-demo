cd src\main\java\

g++ -c -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" com\jni\hello\com_jni_hello_HelloWorldJNI.cpp -o com\jni\hello\com_jni_hello_HelloWorldJNI.o
g++ -shared -o com\jni\hello\native.dll com\jni\hello\com_jni_hello_HelloWorldJNI.o -Wl,--add-stdcall-alias
java -cp . -Djava.library.path=com/jni/hello/ com.jni.hello.HelloWorldJNI

cd ..\..\..\
