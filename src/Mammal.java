/**
* [Mammal.java]
* subclass to Being
* superclass to human and zombie
* contains move, consumePlant and oldAge methods
* Kyle Zhou
*/

abstract class Mammal extends Being {
  
  private boolean movement; 
  private int maxHealth = 2000;
  
  /**
  * Mammal constructor 
  * inherits health, xCoordinate, yCoordinate and age from Being
  */
  Mammal(int health, int xCord, int yCord, int age){
    super(health, xCord, yCord, age);
  }
  
  
  /**
  * getMaxHealth
  * @return int value of maxHealth
  */  
  public int getMaxHealth(){
    return maxHealth;
  }
  
  /**
  * setMovement 
  * This method sets movement to value of parameter
  * @param movement, boolean value
  */
  public void setMovement(boolean movement){
    this.movement = movement;
  }
  
  /**
  * getMovement 
  * @return boolean value of movement
  */
  public boolean getMovement(){
    return movement;
  }
  
  /**
  * move 
  * This method takes in map as parameter.
  * Determines correct direction for a given mammal to move based on results of algorithm
  * @param map, 2D array of object Being representing simulation map
  */
  public void move(Being[][] map){
    int newY;
    int newX;
    boolean newZombie = false; 
    int direction = ((int)(Math.random()*5));  //randomly choose between 5 direction options
    
    if ((direction == 1) && (yCord != map.length-1)){ 
      newY = yCord+1;     
      
      if (map[yCord][xCord] instanceof Human){ //if current position is a Human
        if (map[newY][xCord] instanceof Plant){
          consumePlant(map, xCord, newY); //consume plant if possible
        }  
        else if (map[newY][xCord] instanceof Human){ 
          ((Human)map[yCord][xCord]).procreation(map, xCord, newY, xCord, yCord); // if new position is human, two humans attempt procreation
        }   
        
      } else if (map[yCord][xCord] instanceof Zombie){ //if current position is a Zombie
        if (map[newY][xCord] instanceof Human){
          newZombie = ((Zombie)map[yCord][xCord]).zombieVsHuman(map, xCord, newY, xCord, yCord); //if new position is a human, zombie consumes or infects such human 
        }
      }
      
      if ((map[newY][xCord] == null) || (map[newY][xCord] instanceof Plant) || (newZombie == true)){ //checks if moving is possible
        map[newY][xCord] = map[yCord][xCord]; //sets new position to the value of the old position
        map[yCord][xCord] = null; //sets the old position to null
        yCord = newY; //sets the old value of coordinate to the new value
      } 

    } else if ((direction == 2) && (yCord != 0)){  //Repeats steps if a different direction is chosen
      newY = yCord-1;

      if (map[yCord][xCord] instanceof Human){
        if (map[newY][xCord] instanceof Plant){
          consumePlant(map, xCord, newY); 
        } 
        else if (map[newY][xCord] instanceof Human){
          ((Human)map[yCord][xCord]).procreation(map, xCord, newY, xCord, yCord);
        }
        
      } else if (map[yCord][xCord] instanceof Zombie){
        if (map[newY][xCord] instanceof Human){
          newZombie = ((Zombie)map[yCord][xCord]).zombieVsHuman(map, xCord, newY, xCord, yCord);  
        } 
      }
      
      if ((map[newY][xCord] == null) || (map[newY][xCord] instanceof Plant) || (newZombie == true)){
        map[newY][xCord] = map[yCord][xCord];
        map[yCord][xCord] = null;
        yCord = newY;
      } 

    } else if ((direction == 3) && (xCord != map[0].length-1)){ //Repeats steps if different direction
      newX = xCord+1;

      if (map[yCord][xCord] instanceof Human){
        if (map[yCord][newX] instanceof Plant){
          consumePlant(map, newX, yCord); 
        } 
        else if (map[yCord][newX] instanceof Human){
          ((Human)map[yCord][xCord]).procreation(map, newX, yCord, xCord, yCord);
        }
          
      } else if (map[yCord][xCord] instanceof Zombie){
        if (map[yCord][newX] instanceof Human){
          newZombie = ((Zombie)map[yCord][xCord]).zombieVsHuman(map, newX, yCord, xCord, yCord);  
        }
      }
      
      if (((map[yCord][newX] == null) || (map[yCord][newX] instanceof Plant)) || (newZombie == true)){
        map[yCord][newX] = map[yCord][xCord];
        map[yCord][xCord] = null;
        xCord = newX;
      }
      
    } else if ((direction == 4) && (xCord != 0)){ //Repeat steps if different direction
      newX = xCord-1;

      if (map[yCord][xCord] instanceof Human){
        if (map[yCord][newX] instanceof Plant){
          consumePlant(map, newX, yCord); 
        } 
        else if ((map[yCord][newX] instanceof Human)){
          ((Human)map[yCord][xCord]).procreation(map, newX, yCord, xCord, yCord);
        }
          
      } else if (map[yCord][xCord] instanceof Zombie){
        if (map[yCord][newX] instanceof Human){
          newZombie = ((Zombie)map[yCord][xCord]).zombieVsHuman(map, newX, yCord, xCord, yCord);  
        }
      }
      
      if (((map[yCord][newX] == null) || (map[yCord][newX]) instanceof Plant) || (newZombie == true)){
        map[yCord][newX] = map[yCord][xCord];
        map[yCord][xCord] = null;
        xCord = newX; 
      }
    } 
  }
  
  /**
  * consumePlant 
  * This method accepts the map and new x/y coordinates and checks if a 
  * plant exists at such location. If so, the mammal takes its spot
  * OVERRIDDEN in Human Class
  * @param map, simulation board represtened by a 2D array of object Being
  * @param newX, XCoordinate of potential plant
  * @param newY, YCoordinate of potential plant
  */
  public void consumePlant(Being[][]map, int newX, int newY){
    if (map[newY][newX] instanceof Plant){ 
      map[newY][newX] = null;
    }
  }
  
  /**
  * oldAge 
  * This method accepts the map and current x/y coordinates and checks the age of a
  * given mammal. It then calculates the likelyhood of death based on age.
  * OVERRIDDEN in Human Class
  * @param map, simulation board represtened by a 2D array of object Being
  * @param xCord, XCoordinate of current mammal
  * @param yCord, YCoordinate of current mammal
  */  
  public void oldAge(Being[][] map, int xCord, int yCord){
    int rate = age / age + 3;
    int deathOdds = Creator.randInt(0, rate);
    if (deathOdds == 1){
      map[yCord][xCord] = null;
    }
  }
  
}