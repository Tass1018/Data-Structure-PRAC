package project5;
import java.util.ArrayList;


//valid supply:food, raft, axe.
//OBSTACLES: fallen tree and river.

public class BSTMountain {

  public static RestStop root;

  public BSTMountain() {
    this.root = null;
  }


  public static int height(RestStop node) {
    if (node == null) {
      return -1;
    } else {
      updateHeight(node);
      return node.getHeight();
    }
  }

  //private void updateHeight(RestStop node) {
   // int leftChildHeight = height(root.left);
  //  int rightChildHeight = height(root.right);
   // root.height = max(leftChildHeight, rightChildHeight) + 1;
  //}

  private static void updateHeight(RestStop node) {
    if (node.getLeft() == null && node.getRight() == null) {
      node.setHeight(0);
    } else if (node.getLeft() == null) {
      node.setHeight(node.getRight().getHeight() + 1);
    } else if (node.getRight() == null) {
      node.setHeight(node.getLeft().getHeight() + 1);
    } else {
      node.setHeight(1 + Math.max( node.getLeft().getHeight(), node.getRight().getHeight()));
    }
  }

  public static int balanceFactor(RestStop node) {
    return node.getRight().getHeight() - node.getLeft().getHeight();
  }

  public static RestStop insert(RestStop current, RestStop node) {
    if (current == null) {
      current = node;
      return current;
    }
    if (node.getLabel().compareTo(current.getLabel()) < 0) {
      current.setLeft(insert(current.getLeft(), node));
    }
    if (node.getLabel().compareTo(current.getLabel()) > 0) {
      current.setRight(insert(current.getRight(), node));
    } else {
      return current;
    }
    updateHeight(current);
    return applyRotate(current);
  }

  private static RestStop applyRotate(RestStop node) {
    int balance = balanceFactor(node);
    if (balance > 1) {
      if (balanceFactor(node.getLeft()) < 0) {
        node.setLeft(rotateLeft(node.getLeft()));
      }
      return rotateRight(node);
    }

    if (balance < -1) {
      if (balanceFactor(node.getLeft()) > 0) {
        node.setRight(rotateRight(node.getRight()));
      }
      return rotateLeft(node);
    }
    return node;
  }

  private static RestStop rotateLeft(RestStop node) {
    RestStop rightNode = node.getRight();
    RestStop cNode = rightNode.getLeft();
    rightNode.setLeft(node);
    node.setRight(cNode);
    updateHeight(node);
    updateHeight(rightNode);
    return node;
  }

  private static RestStop rotateRight(RestStop node) {
    RestStop leftNode = node.getLeft();
    RestStop cNode = leftNode.getRight();
    leftNode.setRight(node);
    node.setLeft(cNode);
    updateHeight(node);
    updateHeight(leftNode);
    return node;
  }

}



