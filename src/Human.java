/**
* [Human.java]
* subclass to Mammal
* contains consumePlant and procreation methods
* Kyle Zhou
*/

class Human extends Mammal{
  
  private static final int AGE_OF_CONSENT = 70; //limits procreation
  private boolean male;
  
 /**
  * Human constructor 
  * inherits health, xCoordinate, yCoordinate and age from Mammal
  */
  Human(int health, int xCord, int yCord, int age){
    super(health, xCord, yCord, age);
  }
  
 /**
  * Human constructor 
  * inherits health, xCoordinate, yCoordinate and age from Being
  * sets male to gender 
  */ 
  Human(int health, int xCord, int yCord, int age, boolean gender){
    super(health, xCord, yCord, age);  
    male = gender;
  }
  
/**
  * getMale 
  * @return boolean value of male
  */  
  public boolean getMale() {
    return this.male;
  }
  
  /**
  * consumePlant 
  * This method accepts the map and new x/y coordinates and checks if a 
  * plant exists at such location. If so, the human gains the health of the plant and takes its spot.
  * @param map, simulation board represtened by a 2D array of object Being
  * @param x, XCoordinate of potential plant
  * @param y, YCoordinate of potential plant
  */
  @Override
  public void consumePlant(Being[][] map, int newX, int newY){
    //if (map[newY][newX] instanceof Plant){ 
      ((Human)map[yCord][xCord]).setHealth ( (map[newY][newX]).getHealth() + (map[yCord][xCord]).getHealth() );
    //} 
  }
   
  /**
  * procreation 
  * This method accepts the map, new x/y coordinates and old x/y coordinates 
  * Checks if human procreation is possible and creates a new human if so
  * @param map, simulation board represtened by a 2D array of object Being
  * @param newX, XCoordinate of other human
  * @param newY, YCoordinate of other human
  * @param oldX, xCoordinate of current human
  * @param oldY, yCoordinate of current humand
  */    
  public void procreation(Being[][] map, int newX, int newY, int xCord, int yCord){
    // checks if current human and new human are male and female                                     
    if ( ((((Human)map[yCord][xCord]).male == true ) && (((Human)map[newY][newX]).male == false )) 
          //checks if new human and current human are male and female
          || ((((Human)map[newY][newX]).male == true ) && (((Human)map[yCord][xCord]).male == false )) ){
      //checks if both humans are above or equal to the age of consent
      if ((((map[newY][newX]).age >= AGE_OF_CONSENT)) && ((map[yCord][xCord]).age >= AGE_OF_CONSENT)){
        if (newX+1 < map.length && (map[newY][newX+1] == null)){ //checks if map space is null
          Creator.addHuman(Creator.randInt(80, 120), newX+1, newY, 0);
        } else if (newX-1 >= 0 && ((map[newY][newX-1] == null))){
          Creator.addHuman(Creator.randInt(80, 120), newX-1, newY, 0);
        } else if (newY+1 < map.length && ((map[newY+1][newX] == null))){
          Creator.addHuman(Creator.randInt(80, 120), newX, newY+1, 0);
        } else if (newY-1 >= 0 && ((map[newY-1][newX] == null))){
          Creator.addHuman(Creator.randInt(80, 120), newX, newY-1, 0);
        }          
      }
    }
  }
  

    
}