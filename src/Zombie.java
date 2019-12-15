/**
* [Zombie.java]
* subclass to Being
* contains zombieVsHuman method
* Kyle Zhou
*/

class Zombie extends Mammal {
  
  /**
  * Zombie constructor 
  * inherits health, xCoordinate, yCoordinate and age from Mammal
  */ 
  Zombie(int health, int xCord, int yCord, int age){
    super(health, xCord, yCord, age);
  }
  
  /**
  * zombieVsHuman
  * This method takes in the map, new X/Y coordinates and current x/y coordinates.
  * Using the Comparable interface it determines if the health of the current zombie is
  * less than or equal to the given human at new X/Y
  * It then either updates the zombie health or infects the human
  * It then returns true or false accordingly
  * @return boolean
  */ 
  public boolean zombieVsHuman(Being[][] map, int newX, int newY, int xCord, int yCord){
    if ((map[yCord][xCord]).compareTo(map[newY][newX]) >= 0){ // if zombie health is greater than or equal to human health
      //set the current zombie health to its current health + the health of the human
      ((Zombie)map[yCord][xCord]).setHealth ( (map[newY][newX]).getHealth() + (map[yCord][xCord]).getHealth() );
      return true;
    } else {
      map[newY][newX] = new Zombie(map[newY][newX].getHealth(), newX, newY, 0); // x/y coordinates of human set to new zombie 
      ((Mammal)map[newY][newX]).setMovement(true);  //Cancels the current zombie's turn to prevent spreading too fast
      return false;
    }
  }  

}