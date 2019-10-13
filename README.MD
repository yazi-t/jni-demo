# JNI Demonstration

This application demonstrates basic usages of Java Native Interface (JNI).

There are three usages in this demo.

## Basic hello world
The hello world application implementation is resides in the _jni-hello_ module. It uses javac compiler and g++ compiler to compile the project and run.
Main class of this module calls native library _native.dll_ to print _"hello world"_.

#### Generating header files
JDK includes tools to generate header files for the native method interface.
#####How to generate
> in java 8 and prior using _javah_ command and _java -h_in java 9 and higher.

Native library should be included in the _java.library.path_ to link the library at the run time. Java path is defaulted to system PATH variable
if not explicitly set.

This demo can be run using script-run.bat
#####Steps
1. Run `cd /jni-hello`
2. Run `./script-run.bat` from cmd

## Hash generator
MD5 hash code generate example using C native code to execute hashing algorithm. This example uses maven to compile the project.
`org.codehaus.mojo` `native-maven-plugin` native maven plugin has been used to compile the native library. Since it internally uses the g++/gcc compiler 
g++/gcc installation is a requirement to this plugin. Native library implementation resides in the _c-hash_ module and java code resides in the _java-hash_ module.

_javah_ goal in the maven plugin (above mentioned) has been used to generate the header files of the native code.

#####DLL Extractor
This project bundles native library into the jar file generated. `DLLLoader` class is used to extract native library from jar to directory jar is located 
since Java  doesn't provide a mechanism to load a native library from the jar itself.
> Note: Currently as the name implies this class supports dll extension in windows environment only. Can be modified to extract linux `.so` files with minimal set of changes. 

#####Steps
1. Run `mvn clean install` from the project root
2. Run `cd /java-hash` 
3. Run `./script-run.bat` from cmd

## Native cache
Cache implementation to store java objects in native memory. Like the _hash generator_ this uses same maven plugin to build the project.

Cache is implemented in C++ using a map and can be stores the `jobject` as a global reference.

Native memory cache implementation is an important topic in the java ecosystem since on heap cache increases the heap size and
can be a cause for long GC cycles. Since the native memory management is done manually this resolves that problem.

#####Steps
1. Run `mvn clean install` from the project root
2. Run `cd java-cache-caller`
3. Run `./script-run.bat` from cmd

## Requirements
1. JDK 8 or higher
2. maven installation
3. This project supports only `dll` libraries and windows environment, but easily can be modified to be used in the linux environment.
4. g++/gcc compiler

#####Install gcc/g++ compiler
> In windows environment mingw can be used to compile c/c++ programs. [mingw-w64 for 64bit envirnment](http://mingw-w64.org/doku.php) or [mingw for 32bit environment](http://www.mingw.org/)

> In linux environment to install run `sudo apt install gcc` `sudo apt install g++`

