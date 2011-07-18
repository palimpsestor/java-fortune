package com.slurm.fortune;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Fortune {

  DatFile datFile;
  RandomAccessFile stringFile;

  public Fortune(String datFileName, String stringFileName) {
    try {
      datFile = new DatFile(datFileName);
      stringFile = new RandomAccessFile(stringFileName, "r");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

//  private static void getEndpoints(int index, DatFile file) throws IOException {
//    long seekpoint = 28 + index * 4;
//System.out.println("seekpoint: " + seekpoint);
//    file.seek(seekpoint);
//    start = file.readInt();
//System.out.println("start: " + start);
//    end = file.readInt();
//System.out.println("end: " + end);
//  }

  private String getString(int start) throws IOException {
    StringBuffer retVal = new StringBuffer();
//System.out.println("in display, start: " + start);
    stringFile.seek(start);
//System.out.println("file.length(): " + file.length());
    byte ch = stringFile.readByte();
//int count = 0;
    while (ch != 0 && ch != -1 && ch != '%') {
//      System.out.print((char)ch);
      retVal.append((char)ch);
      ch = stringFile.readByte();
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

  public String getFortune() throws IOException {
    // read past header
    datFile.seek(0);
    int garbage = datFile.readInt();  // version
    int numStr = datFile.readInt();  // numStr
    garbage = datFile.readInt();  // lonLen
    garbage = datFile.readInt();  // shortLen
    garbage = datFile.readInt();  // flags

    // decide which fortune to get
    Random generator = new Random();
    int choice = generator.nextInt(numStr);
//int choice = 109;
//System.out.println("choice: " + choice);

    // find the offset for that string
    long seekpoint = 28 + choice * 4;
//System.out.println("seekpoint: " + seekpoint);
    datFile.seek(seekpoint);
    int start = datFile.readInt();

//System.out.println("start: " + start);
//System.out.println("end: " + end);
//    System.out.println(getString(start, end, stringFile));
    return getString(start);

  }

}