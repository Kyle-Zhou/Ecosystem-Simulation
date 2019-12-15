/**
* [Plant.java]
* subclass to Being
* contains growPlants method
* Kyle Zhou
*/
class Plant extends Being{

  private boolean grown;
  
  /**
  * Plant constructor 
  * inherits health, xCoordinate, yCoordinate and age from Being
  */ 
  Plant(int health, int xCord, int yCord, int age){
    super(health, xCord, yCord, age);
  }
  
  /**
  * getAge
  * @return int value of age
  */ 
  public int getAge(){
    return this.age;
  }
  
  /**
  * getGrown
  * @return boolean value of grown
  */ 
  public boolean getGrown(){
    return grown;
  }
  
  /**
  * setGrown
  * set grown to the value of parameter boolean grown
  */ 
  public void setGrown(boolean grown){
    this.grown = grown;
  }

  /**
  * growPlants  
  * grows plants if space is available 
  * @param map, 2D array of Beings 
  */ 
  public void growPlants(Being[][] map) { 
      if ((xCord != 0) && (map[yCord][xCord - 1] == null) && (Creator.randInt(1,2) == 1)) { //one in two chance for plant to respawn in a given direction
        Creator.addPlant(Creator.randInt(20, 30), xCord-1, yCord, Creator.randInt(0, 10));
      }
      if ((xCord != map[0].length - 1) && (map[yCord][xCord + 1] == null) && (Creator.randInt(1,2) == 1)) {
        Creator.addPlant(Creator.randInt(20, 30), xCord+1, yCord, Creator.randInt(0, 10));
      }
      if ((yCord != 0) && (map[yCord - 1][xCord] == null) && (Creator.randInt(1,2) == 1)) {
        Creator.addPlant(Creator.randInt(20, 30), xCord, yCord-1, Creator.randInt(0, 10));
      }
      if ((yCord != map.length - 1) && (map[yCord + 1][xCord] == null) && (Creator.randInt(1,2) == 1)) {
        Creator.addPlant(Creator.randInt(20, 30), xCord, yCord+1, Creator.randInt(0, 10));
      }
  }
  
}