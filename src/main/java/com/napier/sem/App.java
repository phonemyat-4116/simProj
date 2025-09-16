package com.napier.sem;

public class App {
    public static void main(String[] args)
    {
        System.out.println("Boo yah!");
    }
}

/*
✅ Key points:

Runner’s JDK 20 is only used for compiling outside Docker.

Docker JDK 21 is only used for running the code inside the container.

No error occurs because compiled Java 20 classes are compatible with JDK 21.
 */
