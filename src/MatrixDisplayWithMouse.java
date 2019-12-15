import javax.swing.JFrame; 
import javax.swing.JPanel; 
import java.awt.Toolkit; 
import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseEvent; 
import java.awt.Image; 


/* [MatrixDisplayWithMouse.java]  
 * Kyle Zhou
 * Displays the graphical interface of the 2D array of Beings 
 */ 

class MatrixDisplayWithMouse extends JFrame { 
   
  private int screenWidth, screenHeight, GridToScreenRatio, turns; 
  private Being[][] matrix; 
  
  /**
  * MatrixDisplayWithMouse constructor 
  * inherits title and takes in the 2D array of Beings as parameters
  * sets screenWidth and screenHeight
  * Establishes GridToScreenRatio
  */  
  MatrixDisplayWithMouse(String title, Being[][] matrix) { 
    super(title); 
   
    this.matrix = matrix; 
    screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width; 
    screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height; 
    GridToScreenRatio = screenHeight / (matrix.length + 1);  //ratio to fit in screen as square map 
     
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize()); 
     
    this.add(new MatrixPanel()); 
     
    this.setVisible(true); 
  } 

  /**
  * refresh  
  * Refreshes the graphics
  * increments turns by 1
  */  
   public void refresh() {  
    this.repaint(); 
    turns++;
  }
   
  // Inner class
  /**
  * MatrixPanel  
  * inherits from JPanel
  * Initalizes images 
  * paintComponent method
  */  
  class MatrixPanel extends JPanel {

    //Image initialization 
    Image female = Toolkit.getDefaultToolkit().getImage("Female.png"); 
    Image male = Toolkit.getDefaultToolkit().getImage("Male.png"); 
    Image plant = Toolkit.getDefaultToolkit().getImage("Plant.png");
    Image zombie = Toolkit.getDefaultToolkit().getImage("Zombie.png");
    Image black = Toolkit.getDefaultToolkit().getImage("Black.png");
    Image gameOver = Toolkit.getDefaultToolkit().getImage("Game over screen.png");
   
    
    /**
    * MatrixPanel Constructor 
    * adds MatrixPanelMouseListener
    */  
    MatrixPanel() { 
      addMouseListener(new MatrixPanelMouseListener());
    }
    

    /**
    * paintComponent  
    * Outputs all graphical componenets 
    * @param g, of class Graphics
    */  
    public void paintComponent(Graphics g) {      

      super.repaint();
      setDoubleBuffered(true); 

      int numMales = 0, numFemales = 0, numZombies = 0, numPlants = 0;
      int highestMale = 0, highestFemale = 0, highestZombie = 0, highestPlant = 0;
      int oldestMale = 0, oldestFemale = 0, oldestZombie = 0, oldestPlant = 0;
      
      for (int i = 0; i < matrix.length; i++) {  
        for (int j = 0; j < matrix[i].length; j++) {  
          
         g.drawImage(black,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this); //set default background image to black
         
         if ((matrix[i][j] instanceof Human) && (((Human)matrix[i][j]).getMale() == true)) { //determines if the current positon of the matrix is a male
            numMales++;
            if (matrix[i][j].health > highestMale){ // if health is greater than that of the current highest male 
              highestMale = matrix[i][j].health;    // set its health to the highest
            }
            if (matrix[i][j].age > oldestMale){ // if age is greater than that of the current highest male
              oldestMale = matrix[i][j].age;    // set its age to the highest
            }
            g.drawImage(male,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this); //draw the male image
            
          } else if ((matrix[i][j] instanceof Human) && (((Human)matrix[i][j]).getMale() == false)) { // Repeats for females
            numFemales++;
              if (matrix[i][j].health > highestFemale){
                highestFemale = matrix[i][j].health;
              }    
              if (matrix[i][j].age > oldestFemale){
                oldestFemale = matrix[i][j].age;
              }
            g.drawImage(female,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this); 
            
          } else if (matrix[i][j] instanceof Zombie) { // Repeats for zombies
            numZombies++;
            if ((matrix[i][j]).health > highestZombie){
              highestZombie = matrix[i][j].health;
            }
            if (matrix[i][j].age > oldestZombie){
              oldestZombie = matrix[i][j].age;
            }
            g.drawImage(zombie,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this); 
            
          } else if (matrix[i][j] instanceof Plant) { // Repeats for plants
            numPlants++;
            if (matrix[i][j].age > highestPlant){
              highestPlant = matrix[i][j].health;
            }
            if (matrix[i][j].age > oldestPlant){
              oldestPlant = matrix[i][j].age;
            }
            g.drawImage(plant,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);             
          }
        } 
      }
      
      //Draw text for number of turns
      g.drawString("Number of Turns: " + turns, (screenWidth * 63) / 100, 50);  //begin text at 63% of screen width
      
      //Draw text for greatest health
      g.drawString("Strongest Male: health = " + highestMale, (screenWidth * 63) / 100, 100);
      g.drawImage(male, (screenWidth * 80) / 100, 88, 14, 14, this);
      g.drawString("Strongest Female: health = " + highestFemale, (screenWidth * 63) / 100, 125);
      g.drawImage(female, (screenWidth * 80) / 100, 113, 14, 14, this);
      g.drawString("Strongest Zombie: health = " + highestZombie, (screenWidth * 63) / 100, 150); 
      g.drawImage(zombie, (screenWidth * 80) / 100, 138, 15, 15, this);
      g.drawString("Strongest Plant: age = " + highestPlant, (screenWidth * 63) / 100, 175);
      g.drawImage(plant, (screenWidth * 80) / 100, 163, 16, 16, this);
      
      //Draw text for greatest age
      g.drawString("Highest Male age: = " + oldestMale, (screenWidth * 82) / 100, 99);
      g.drawString("Highest Female age: = " + oldestFemale, (screenWidth * 82) / 100, 124);
      g.drawString("Highest Zombie age: = " + oldestZombie, (screenWidth * 82) / 100, 149);
      g.drawString("Highest Plant age: = " + oldestPlant, (screenWidth * 82) / 100, 174);
      
      //Draw text for number of each biological group
      g.drawString("Number of Males: " + numMales, (screenWidth * 63) / 100, 380); 
      g.drawString("Number of Females: " + numFemales, (screenWidth * 63) / 100, 470); 
      g.drawString("Number of Zombies: " + numZombies, (screenWidth * 63) / 100, 560); 
      g.drawString("Number of Plants: " + numPlants, (screenWidth * 63) / 100, 650); 
      
      //Draw the graph
      g.setColor(Color.RED);   
      //Scales graph according to map length
      if (((numMales * matrix.length ) / (matrix.length * 2)) < 450){  //sets limit to graph to prevent exceeding the screen
        g.fillRect((screenWidth * 63) / 100, 390, (numMales * matrix.length ) / (matrix.length * 2) + 2, 60);       
      } else {
        g.fillRect((screenWidth * 63) / 100, 390, 450, 60);       
      }
      g.setColor(Color.MAGENTA); 
      if (((numFemales * matrix.length ) / (matrix.length * 2)) < 450){
      g.fillRect((screenWidth * 63) / 100, 480, (numFemales * matrix.length ) / (matrix.length * 2) + 2, 60);  
      } else {
        g.fillRect((screenWidth * 63) / 100, 480, 450, 60);          
      }
      g.setColor(Color.BLUE); 
      if (((numZombies * matrix.length ) / (matrix.length * 2)) < 450){     
      g.fillRect((screenWidth * 63) / 100, 570, (numZombies * matrix.length ) / (matrix.length * 2) + 2, 60); 
      } else {
      g.fillRect((screenWidth * 63) / 100, 570, 450 + 2, 60);         
      }
      g.setColor(Color.GREEN);       
      if (((numPlants * matrix.length ) / (matrix.length * 2)) < 450){
        g.fillRect((screenWidth * 63) / 100, 660, (numPlants * matrix.length ) / (matrix.length * 2) + 2, 60);
      } else {   
        g.fillRect((screenWidth * 63) / 100, 660, 450, 60);
      }
      
      if (numMales + numFemales == 0){ // No humans left in this case
        g.drawImage(gameOver, 0, 0, screenWidth, screenHeight, this); //display game over screen
      }   
    }
  }
  
  /**
  * MatrixPanelMouseListener  
  * Mouse Listener
  */ 
  class MatrixPanelMouseListener implements MouseListener{ 

    /**
    * mousePressed  
    * Determines x and y coordinate of the mouse 
    * If occurance of mousePressed and all conditions are met, a new zombie is spawned
    * @param e, of class MouseEvent
    */ 
    public void mousePressed(MouseEvent e) {
      int x = e.getPoint().x/GridToScreenRatio;
      int y = e.getPoint().y/GridToScreenRatio;
      if ((y < matrix.length) && (x < matrix.length)){
        if (matrix[y][x] == null){
          Creator.addZombie(Creator.randInt(80,120), x, y, 0); 
        } 
      }
    }

    public void mouseReleased(MouseEvent e) {
      //System.out.println("Mouse released; # of clicks: " + e.getClickCount());
    }

    public void mouseEntered(MouseEvent e) {
       //System.out.println("Mouse entered");
    }

    public void mouseExited(MouseEvent e) {
       //System.out.println("Mouse exited");
    }

    public void mouseClicked(MouseEvent e) {
      //System.out.println("Mouse clicked (# of clicks: "+ e.getClickCount() + ")");
    }
  }
}

  