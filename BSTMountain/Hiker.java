package project5;
import java.util.ArrayList;
import java.util.*;  

public class Hiker {

  private static int countAxe = 0;
  private static int countRaft = 0;
  private static int countFood = 0;
  public static RestStop start = null;
  public static ArrayList<ArrayList<String>> bundle = new ArrayList<ArrayList<String>>();
  public static ArrayList<String> results = new ArrayList<String>();
  

  
  public Hiker(BSTMountain bstmount) {
    this.start = bstmount.root;
  }



  public static void travel(RestStop curr, ArrayList<String> result) {

    if (curr == null) {
      if (result.isEmpty() == false && result.size() != 0) {
        bundle.add(result);
      }
    }

    ArrayList<String> history1 = new ArrayList<String>(result);
    ArrayList<String> history2 = new ArrayList<String>(result);
  
    countAxe += Collections.frequency(curr.getSupply(), "axe");
    countRaft += Collections.frequency(curr.getSupply(), "raft");
    countFood += Collections.frequency(curr.getSupply(), "food");

    if (curr.getObstacle() == null || curr.getObstacle().isEmpty() == true) {
      if (hasFood() == true) {
        history1.add(curr.getLabel());
        history2.add(curr.getLabel());
        travel(curr.getLeft(), history1);
        travel(curr.getRight(), history2);
      }
    }

    if (curr.getObstacle().contains("river")) {
      if (curr.getObstacle().contains("fallen tree")) {
        if (passFallenTree() == true && hasFood() == true && passRiver() == true) {
          history1.add(curr.getLabel());
          history2.add(curr.getLabel());
          travel(curr.getLeft(), history1);
          travel(curr.getRight(), history2);
        }
      }
      if (passRiver() == true && hasFood() == true) {
        history1.add(curr.getLabel());
        history2.add(curr.getLabel());
        travel(curr.getLeft(), history1);
        travel(curr.getRight(), history2);
      }
    }

    if (curr.getObstacle().contains("fallen tree")) {
      if (passFallenTree() == true && hasFood() == true) {
        history1.add(curr.getLabel());
        history2.add(curr.getLabel());
        travel(curr.getLeft(), history1);
        travel(curr.getRight(), history2);
      }
    }
    return;
  }

  private static boolean hasFood() {
    if (countFood <= 0) {
      return false;
    } else {
      countFood--;
      return true;
    }
  }

  private static boolean passFallenTree() {
    if (countAxe <= 0) {
      return false;
    } else {
      countAxe--;
      return true;
    }
  }

  private static boolean passRiver() {
    if (countRaft <= 0) {
      return false;
    } else {
      countRaft--;
      return true;
    }
  }


  public static void print(ArrayList<ArrayList<String>> bundles) {
    for (int y = 0; y < bundles.size(); y++) {
      int bound = bundles.get(y).size();
      String way = null;
      for (int x = 0; x < bound; x++) {
        way = way + bundles.get(y).get(x) + " ";
      }
      System.out.println(way);
    }
  }
}
