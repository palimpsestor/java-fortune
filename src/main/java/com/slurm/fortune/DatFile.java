package com.slurm.fortune;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class DatFile extends RandomAccessFile {

  public DatFile(String s) throws FileNotFoundException {
    super(s, "r");
  }

}