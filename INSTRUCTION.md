# Setup and Run Guide

This guide provides the commands to compile and run this Java application.

**Note:** All commands should be executed from the root directory of the project.

## 1. Compilation

To compile the source code, use the following command. This will create an `out` directory containing the compiled `.class` files.

```bash
javac -d out -sourcepath src src/Main.java
```

## 2. Running the Application

Once the code is compiled, you can run the application with this command:

```bash
java -cp out Main
```
