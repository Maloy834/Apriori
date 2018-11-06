package apriori;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
      Apriori ap=new Apriori();
      ap.readFile();
      ap.PrintC1();
      ap.printL1();
      ap.GenerateC2List();
      ap.printC2();
      ap.printL2();
      
      ap.GenerateC3List();
      ap.calculateConfidence();
	}

}
