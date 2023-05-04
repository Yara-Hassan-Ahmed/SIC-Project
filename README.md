# SIC-Project

This SIC project is a two-pass assembler that takes assembly code in SIC format as input, and produces the corresponding object code. The project is written in Java and consists of two main passes, with each pass performing a specific set of tasks.

# Prerequisites
  To run this project, you need Java installed on your computer. If Java is not installed, you can download it from the official Java website: https://www.java.com/en/download/

# How to use
To use this project, you need to do the following steps:

  Place the input file, inSIC.txt, in the same directory as the Java file.
  Compile the Java file by typing the following command in the command prompt: javac Main.java
  Run the Java file by typing the following command in the command prompt: java Main
  The output will be displayed on the command prompt, and also written to the output file, outSIC.txt, in the same directory as the Java file.

# File Structure

  Main.java: This is the main Java file that contains the code for the two-pass assembler.
  inSIC.txt: This is the input file that contains the assembly code in SIC format.
  outSIC.txt: This is the output file that contains the corresponding object code.

# Code Explanation
The Java code is divided into two main passes, each performing a specific set of tasks.

  Pass 1

    Read from the input file and store the data in ArrayLists.
    Calculate the location counter (LOCCTR) for each instruction.
    Generate the symbol table.
    Write the intermediate file.

  Pass 2

    Read from the intermediate file and generate the object code.
    Write the object code to the output file.

# Code Comments
  The code contains comments that explain the purpose of each line of code. These comments are meant to help you understand how the code works, and how the two-pass assembler works.

# Conclusion
  This SIC project is a useful tool for converting assembly code in SIC format to object code. The project is written in Java and consists of two main passes, each performing a specific set of tasks. The code contains comments that explain the purpose of each line of code, making it easy to understand and modify.
