/**
* [Being.java]
* initalizes health, x coordinate, y coordinate and age
* Kyle Zhou
*/

abstract class Being implements Comparable{

  public int health;
  public int xCord;
  public int yCord;
  public int age;

 /**
  * Being constructor 
  * This method sets health, xCoordinate, yCoordinate and age 
  */
  Being(int health, int xCord, int yCord, int age){
    this.health = health;
    this.xCord = xCord;
    this.yCord = yCord;
    this.age = age;
  }
  
 /**
  * getHealth 
  * @return int value of health
  */    
  public int getHealth() {
    return this.health;
  }
  
/**
  * setHealth 
  * This method sets health to value of num
  * @param num, int value
  */  
  public void setHealth(int num) {
    this.health = num;
  }
  
 /**
   * compareTo
   * This method compares the health of two Being's
   * @Param o, a Being object
   * @Return an integer based on the difference of health
   * positive if smaller than and negative if greater than.
   */
  public int compareTo(Object o){
    Being b = ((Being) o);
    return (int)(this.health - b.health);
  }
  
}





