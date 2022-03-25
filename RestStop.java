package project5;
import java.util.ArrayList;

public class RestStop {
  
  private static String label;
  private static ArrayList<String> supply;
  private static ArrayList<String> obstacle;
  private static RestStop left;
  private static RestStop right;

  private static int height;

  public RestStop(String label, ArrayList<String> supplies, ArrayList<String> obstacles) {
    this.label = label;
    this.supply = supplies;
    this.obstacle = obstacles;
    this.left = null;
    this.right = null;
  }



  public String getLabel() {
    return label;
  }
  public ArrayList<String> getSupply() {
    return supply;
  }
  public ArrayList<String> getObstacle() {
    return obstacle;
  }
 
  public RestStop getLeft() {
    return left;
  }
  public RestStop getRight() {
    return right;
  }

  public int getHeight() {
    return height;
  }


  public void setLeft(RestStop node){
    this.left = node;
  }

  public void setRight(RestStop node){
    this.right = node;
  }

  public void setHeight(int h){
    this.height = h;
  }



}
