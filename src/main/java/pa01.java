package main.java;/*=============================================================================
|   Assignment:  pa01 - Encrypting a plaintext file using the Vigenere cipher
|
|       Author:  Sean Merkel
|     Language:  c, c++, Java
|
|   To Compile:  javac pa01.java
|                gcc -o pa01 pa01.c
|                g++ -o pa01 pa01.cpp
|
|   To Execute:  java -> java pa01 kX.txt pX.txt
|          or    c++  -> ./pa01 kX.txt pX.txt
|          or    c    -> ./pa01 kX.txt pX.txt
|                         where kX.txt is the keytext file
|                           and pX.txt is plaintext file
|
|         Note:  All input files are simple 8 bit ASCII input
|
|        Class:  CIS3360 - Security in Computing - Summer 2021
|   Instructor:  McAlpin
|     Due Date:  per assignment
|+=============================================================================*/
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.*;

public class pa01{
    public static void main(String[] args) throws IOException {
        char[] alphabetArr = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        File inFile = new File( args[ 0 ]);
        try {
            String keyLine = "";
            BufferedReader theBr = new BufferedReader (new FileReader(inFile));
            String theLine;
            while ((theLine = theBr.readLine()) != null) {
                String temp = "";
                for (char c : theLine.toCharArray()) {
                    if (!(Character.isAlphabetic(c))) {
                        continue;
                    }
                    else if(c != 'a' && c != 'b' && c != 'c' && c != 'd' && c != 'e' && c != 'f' && c != 'g' && c != 'h' && c != 'i' && c != 'j' && c != 'k' && c != 'l' && c != 'm' && c != 'n' && c != 'o' && c != 'p' && c != 'q' && c != 'r' && c != 's' && c != 't' && c != 'u' && c != 'v' && c != 'w' && c != 'x' && c != 'y' && c != 'z' && c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E' && c != 'F' && c != 'G' && c != 'H' && c != 'I' && c != 'J' && c != 'K' && c != 'L' && c != 'M' && c != 'N' && c != 'O' && c != 'P' && c != 'Q' && c != 'R' && c != 'S' && c != 'T' && c != 'U' && c != 'V' && c != 'W' && c != 'X' && c != 'Y' && c != 'Z')
                    {
                        continue;
                    }
                    else if (c >= 'A' && c <= 'Z') {
                        c = Character.toLowerCase(c);
                        temp += c;
                    }
                    else {
                        temp += c;
                    }
                }
                keyLine += temp;
            }
            theBr.close();
            try {
                inFile = new File( args[ 1 ]);
                BufferedReader br = new BufferedReader (new FileReader(inFile));
                String testLine = "";
                int xCount = 0;
                String line;
                while ((line = br.readLine()) != null) {
                    String temp = "";
                    for (char c : line.toCharArray())
                    {
                        if (!(Character.isAlphabetic(c))) {
                            continue;
                        }
                        else if(c != 'a' && c != 'b' && c != 'c' && c != 'd' && c != 'e' && c != 'f' && c != 'g' && c != 'h' && c != 'i' && c != 'j' && c != 'k' && c != 'l' && c != 'm' && c != 'n' && c != 'o' && c != 'p' && c != 'q' && c != 'r' && c != 's' && c != 't' && c != 'u' && c != 'v' && c != 'w' && c != 'x' && c != 'y' && c != 'z' && c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E' && c != 'F' && c != 'G' && c != 'H' && c != 'I' && c != 'J' && c != 'K' && c != 'L' && c != 'M' && c != 'N' && c != 'O' && c != 'P' && c != 'Q' && c != 'R' && c != 'S' && c != 'T' && c != 'U' && c != 'V' && c != 'W' && c != 'X' && c != 'Y' && c != 'Z')
                        {
                            continue;
                        }
                        else if (c >= 'A' && c <= 'Z') {
                            c = Character.toLowerCase(c);
                            temp += c;
                            xCount++;
                        } else {
                            temp += c;
                            xCount++;
                        }
                    }
                    testLine += temp;
                }
                xCount = 512 - xCount;
                for (int i = 0; i < xCount; i++) {
                    testLine += "x";
                }
                br.close();
                String cipherLine = "";
                int j = 0;
                int skipLine = 0;
                char[] curPlain = testLine.toCharArray();
                char[] curKey = keyLine.toCharArray();
                for(int i = 0; i < 512; i++)
                {
                    char plainChar = curPlain[i];
                    char keyChar = curKey[j];
                    int cipherInt = (((keyChar - 'a') + (plainChar - 'a')) % 26);
                    if(cipherInt < 0)
                    {
                        cipherInt = cipherInt * -1;
                    }
                    char cipherChar = alphabetArr[cipherInt];
                    cipherLine += cipherChar;
                    skipLine++;
                    if(skipLine % 80 == 0)
                    {
                        cipherLine += '\n';
                    }
                    if(j == keyLine.length() - 1)
                    {
                        j = 0;
                    }
                    else
                    {
                        j++;
                    }
                }
                skipLine = 0;
                System.out.println("\nVigenere Key:\n");
                for (char c : keyLine.toCharArray())
                {
                    System.out.print(c);
                    skipLine++;
                    if(skipLine % 80 == 0)
                    {
                        System.out.print('\n');
                    }
                }
                skipLine = 0;
                System.out.println("\n\n\nPlaintext: \n");
                for (char c : testLine.toCharArray())
                {
                    System.out.print(c);
                    skipLine++;
                    if(skipLine % 80 == 0)
                    {
                        System.out.print('\n');
                    }
                }
                System.out.println("\n\n\nCiphertext: \n\n" + cipherLine);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}

/*=============================================================================
|     I Sean Merkel (se758574) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied  or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
 +=============================================================================*/
