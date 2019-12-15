/**
* [Creator.java]
* controls logistics of program 
* contains addHuman, addZombie and addPlant methods
* Kyle Zhou
*/

class Creator {
  
  public static Being[][] matrix;
  
 /**
  * Creator constructor 
  * sets matrix to map
  */
  Creator(Being[][] map) {
    this.matrix = map;
  }
  
  /**
  * randInt 
  * This method accepts a minimum and maximum int and returns a random 
  * integer between the two.
  * @param min, minimum number in the set of numbers to be selected from.
  * @param max, maximum number in the set of numbers to be selected from.
  * @return the random int
  */
  public static int randInt(int min, int max){
    int x = (int)(Math.random() * ((max - min) + 1)) + min; //random number between max and min
    return x;
  }
  
  /**
  * getRandomBoolean 
  * This method randomly selects between 1 and 2. If 1 returns true, if 2 returns false.
  * @return random boolean
  */ 
  public static boolean getRandomBoolean() {
    int x = (int)(Math.random() * ((2 - 1) + 1)) + 1;  // selects 1 or 2 randomly
    if (x == 1){
      return true;
    } else {
      return false;
    }
  }
  
  /**
  * addHuman 
  * This method accepts x and y coordinates and checks if a new Human is able to be
  * spawned at such location.
  * @param health, int for Human health
  * @param x, XCoordinate
  * @param y, YCoordinate
  * @param age, int for Human age
  * @return true if human has been spawned
  */
  public static boolean addHuman(int health, int x, int y, int age){
    if (matrix[y][x] == null){
      matrix[y][x] = new Human(health, x, y, age, getRandomBoolean()); //takes in random boolean as parameter representing gender
      return true;
    } else {
      return false;
    }
  }
  
  /**
  * addZombie
  * This method accepts x and y coordinates and checks if a new Zombie is able to be
  * spawned at such location.
  * @param health, int for Zombie health
  * @param x, XCoordinate
  * @param y, YCoordinate
  * @param age, int for Zombie age
  * @return true if Zombie has been spawned
  */  
  public static boolean addZombie(int health, int x, int y, int age) {
    if (matrix[y][x] == null){
      matrix[y][x] = new Zombie(health, x, y, age); 
      return true;
    } else {
      return false;
    }
  }
 
  /**
  * addPlant 
  * This method accepts x and y coordinates and checks if a new Plant is able to be
  * spawned at such location.
  * @param health, int for plant Nutritional Value
  * @param x, XCoordinate
  * @param y, YCoordinate
  * @param age, int for plant age
  * @return true if Plant has been spawned
  */
  public static boolean addPlant(int health, int x, int y, int age) {
    if (matrix[y][x] == null){
      matrix[y][x] = new Plant(health, x, y, age); 
      return true;
    } else {
      return false;
    }
  }
  
}