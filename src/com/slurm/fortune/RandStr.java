package com.slurm.fortune;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class RandStr {

  static int version;
  static int numStr;
  static int longLen;
  static int shortLen;
  static int flags;

  static int start;
  static int end;

  public static void main(String args[]) {
    try {
      // getargs

      String datFileName = "c:\\java\\fortune\\zippy.dat";
      String stringFileName = "c:\\java\\fortune\\zippy";
  
      DatFile datFile = new DatFile(datFileName);
      RandomAccessFile stringFile = new RandomAccessFile(stringFileName, "r");

      version = datFile.readInt();
      numStr = datFile.readInt();
      longLen = datFile.readInt();
      shortLen = datFile.readInt();
      flags = datFile.readInt();
//System.out.println("version: " + version);
//System.out.println("numStr: " + numStr);
//System.out.println("longLen: " + longLen);
//System.out.println("shortLen: " + shortLen);
//System.out.println("flags: " + flags);
      getFortune(datFile, stringFile);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void getEndpoints(int index, DatFile file) throws IOException {
    long seekpoint = 28 + index * 4;
//System.out.println("seekpoint: " + seekpoint);
    file.seek(seekpoint);
    start = file.readInt();
//System.out.println("start: " + start);
    end = file.readInt();
//System.out.println("end: " + end);
  }

  private static String getString(int start, int end, RandomAccessFile file) throws IOException {
    StringBuffer retVal = new StringBuffer();
//System.out.println("in display, start: " + start);
    file.seek(start);
//System.out.println("file.length(): " + file.length());
    byte ch = file.readByte();
//int count = 0;
    while (ch != 0 && ch != -1 && ch != '%') {
//      System.out.print((char)ch);
      retVal.append((char)ch);
      ch = file.readByte();
//count++;
    }
//    System.out.println();
    return retVal.toString().trim();
  }

  private static char unrot(char ch) {
    if ((ch >= 'A') && (ch <= 'Z')) {
      ch = (char) ('A' + (ch - 'A' + 13) / 26);
    }
    else
    if ((ch >= 'A') && (ch <= 'Z')) {
      ch = (char) ('A' + (ch - 'A' + 13) / 26);
    }
    return ch;
  }

  private static void getFortune(DatFile datFile, RandomAccessFile stringFile) throws IOException {
    Random generator = new Random();
    int choice = generator.nextInt(numStr);
//int choice = 109;
//System.out.println("choice: " + choice);

    getEndpoints(choice, datFile);
//System.out.println("start: " + start);
//System.out.println("end: " + end);
    System.out.println(getString(start, end, stringFile));

  }

}