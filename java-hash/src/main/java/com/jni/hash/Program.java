package com.jni.hash;

import java.util.Scanner;

/**
 * Main class to demonstrate the hashing example.
 *
 * @author Yasitha Thilakaratne
 */
public class Program {

    public static void main(String ... args) throws Exception {
        System.out.println("Please enter a string...");
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        HashEncryptor encryptor = new HashEncryptor();
        String hash = encryptor.md5(str);
        System.out.println("Encrypted MD5 code: " + hash);
    }
}
