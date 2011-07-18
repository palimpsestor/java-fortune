package com.slurm.fortune;

public class FortuneTester {

  public static void main(String args[]) {
    try {
      Fortune f = new Fortune("c:\\slurm\\fortune\\zippy.dat", "c:\\slurm\\fortune\\zippy");
      System.out.println(f.getFortune());      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}