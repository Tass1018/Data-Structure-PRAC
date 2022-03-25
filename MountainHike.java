package project5;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MountainHike {

  public static String[] splitInputLine(String textLine) {
    if (textLine == null ) {
          return null;
    }              
    String [] entries = null;

    entries = textLine.split("\\s");

    return entries;
  }



  public static void main(String[] args) throws IllegalArgumentException {

    if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
    File file = new File(args[0]); 
		if (!file.exists()){
			System.err.println("Error: the file "+file.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!file.canRead()){
			System.err.println("Error: the file "+file.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}

    Scanner input = null; 
  

    try {
			input = new Scanner(file) ;
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file "+file.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
    
    ArrayList<RestStop> list = new ArrayList<RestStop>();
		String line = null; 
		String LABEL = null;
		ArrayList<String> SUPPLIES = new ArrayList<String>(); 
		ArrayList<String> OBSTACLES = new ArrayList<String>();
    RestStop current = null;
    ArrayList<String> supplyKW = new ArrayList<String>();
    supplyKW.add("food");
    supplyKW.add("raft");
    supplyKW.add("axe");
    ArrayList<String> obsKW = new ArrayList<String>();
    obsKW.add("fallen tree");
    obsKW.add("river");




    while (input.hasNextLine()) {
			try { 
				line = input.nextLine(); 
        String[] info = splitInputLine(line);
				LABEL = info[0]; ///判断 label的问题
        int l = info.length;


        int i = 1;
        
       

        //出现apple, ignore apple only ->while1
        while (i < l && !(obsKW.contains(info[i].trim()))) {
          if (supplyKW.contains(info[i].trim())) {
             SUPPLIES.add(info[i].trim());
          }
          i++;
        }
        while (i < l) {
          if (obsKW.contains(info[i].trim())) {
            OBSTACLES.add(info[i].trim());
          }    
          i++;
        }

			}
			catch (NoSuchElementException ex ) {
				//caused by an incomplete or miss-formatted line in the input file				
				continue; 	
			}
			catch (NumberFormatException ex) {	
				continue;
			}
			catch (ArrayIndexOutOfBoundsException ex) {
				continue;
			}
      try {
				current = new RestStop(LABEL, SUPPLIES, OBSTACLES);
				list.add(current);
        SUPPLIES.clear();
        OBSTACLES.clear();    
			}
			catch (IllegalArgumentException ex ) {
				//ignore this exception and skip to the next line 
			}
		}

    BSTMountain mountain = new BSTMountain();
    for (int i = 0; i < list.size(); i++) {
      mountain.insert(mountain.root, list.get(i)); 
    }
    System.out.println(list.size());


    Hiker hiker = new Hiker(mountain);
    RestStop o = hiker.start;
    hiker.travel(o, hiker.results);
    hiker.print(hiker.bundle);
    System.exit(1);

  }
}
