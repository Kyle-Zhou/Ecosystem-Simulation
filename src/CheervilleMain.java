/**
* [CheervilleMain.java]
* ICS36U
* Dec 5, 2019
* @author Kyle Zhou 
* 
* Zombie, Plant, Human ecosystem simulation
* Takes in number of humans, number of zombies and plant spawn rate as input
* Simulation is displayed through JFrame/JPanel along with corresponding stats/graphics
* 
*/

import java.util.Scanner;

class CheervilleMain { 
  
  public static void main(String[] args) { 
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Enter number of rows and columns: ");
    //Optimized Value: 30
    int length = input.nextInt();  
    System.out.println("Enter amount of humans: ");
    //Optimized Value: 80
    int numHumans = input.nextInt();
    System.out.println("Enter amount of zombies: ");
    //Optimized Value: 7
    int numZombies = input.nextInt();
    System.out.println("Enter plant spawn rate between 1-100: "); 
    //Optimized Value: 80
    int plantSpawnRate = input.nextInt();
    
    boolean addingObject;  
    int numPlants;
    int x;
    int y;
    int health;
    int age = 0;
    int counter = 0;
    Being[][] map = new Being[length][length];

    Creator creator = new Creator(map);
    MatrixDisplayWithMouse display = new MatrixDisplayWithMouse("Human vs. Zombie simulation", map);
    
    for (int i = 0; i < numHumans; i++){
      x = creator.randInt(0,map.length-1); //Random x and y coordinate within map boundaries
      y = creator.randInt(0,map[0].length-1);
      health = creator.randInt(140, 170);
      addingObject = creator.addHuman(health, x, y, 0); 
      if (addingObject == false){ //if addHuman returns false, try to place another human in a different spot
        i--;
      }
    }
    for (int  j = 0; j < (length * 3) / 2; j++){
      x = creator.randInt(0,map.length-1);
      y = creator.randInt(0,map[0].length-1);
      health = creator.randInt(20, 45);
      age = creator.randInt(0, 20);
      addingObject = creator.addPlant(health, x, y, age);
      if (addingObject == false){ 
        j--;
      }
    }
    for (int k = 0; k < numZombies; k++){
      x = creator.randInt(0,map.length-1);
      y = creator.randInt(0,map[0].length-1);
      health = creator.randInt(120, 160);
      addingObject = creator.addZombie(health, x, y, 0);
      if (addingObject == false){ 
        k--;
      }
    }
    
    while(numHumans > 0) {     
      
      for(int l = 0; l < map.length; l++){        
        for(int m = 0; m < map[l].length; m++){
          
          if ((map[l][m] instanceof Mammal) && (((Mammal)map[l][m]).getMovement() == false)) { 
            ((Mammal)map[l][m]).setMovement(true); //prevent moving multiple steps at once     
            ((Mammal)map[l][m]).move(map);    
          } else if ((map[l][m] instanceof Plant) && (((Plant)map[l][m]).getGrown() == false)){ 
            if (((Plant)map[l][m]).age > 100-plantSpawnRate){ 
              if (!(plantSpawnRate == 0)){
                ((Plant)map[l][m]).setGrown(true);  //prevents plants from respawning each other
                ((Plant)map[l][m]).growPlants(map);
              }
            }
          } 
        }
      }
            
      numHumans = 0;
      numPlants = 0;
      for (int n = 0; n < map.length; n++) {
        for (int o = 0; o < map[n].length; o++) {
          if (map[n][o] instanceof Being){
            ((Being)map[n][o]).age += 1;                  
          } 
          if ((map[n][o] instanceof Mammal) && (((Mammal)map[n][o]).getMovement() == true)) { 
            ((Mammal)map[n][o]).setMovement(false); //allows mammals to move again
            ((Mammal)map[n][o]).health -= 1;  
            
            if (((Mammal)map[n][o]).health == 0) { 
              map[n][o] = null;   //erase mammal from board (mammal dies) once health reaches 0
            } else if (((Mammal)map[n][o]).age > 300) { 
              ((Mammal)map[n][o]).oldAge(map, o, n); //likelyhood to die increases beyond this threshold 
            } else if (((Mammal)map[n][o]).health > ((Mammal)map[n][o]).getMaxHealth()){
              map[n][o] = null;
            }
          
          }
          if (map[n][o] instanceof Human){  //Counting number of humans to determine if simulation should continue 
              numHumans++;
          } if (map[n][o] instanceof Plant){
              numPlants++;
          }
        }
      }
      counter++;
      if ((numPlants < (length * 3) / 2) || (counter % 20 == 0)){  // if plants drop below a given threshold add more OR 
                                                                  //if counter is a multiple of 20
        creator.addPlant(creator.randInt(10, 20), creator.randInt(0,map.length-1), creator.randInt(0,map[0].length-1), 0);
      }
      
      display.refresh(); 
      try{ Thread.sleep(100); }catch(Exception e) {};  //Small delay  
      
    }   
  } 
}
  