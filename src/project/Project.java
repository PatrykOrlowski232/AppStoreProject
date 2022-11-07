package project;
import generator.Generator;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Project {

    Double price;
   public  String name;
    Boolean done;
   public  int[] pointsToDo = new int[6];
    int deadline;
    int timeofpayot;
    String client;
  public  int dificultLevel;


    Generator generator = new Generator();
    public Project() throws FileNotFoundException , IOException {

     generator.dice = generator.rollDice(3);

     switch (generator.dice) {
      case 0 -> {
       this.dificultLevel = 1;
       this.deadline = 20;
       this.price = 10000.0;
      }
      case 1 -> {
       this.dificultLevel = 2;
       this.deadline = 30;
       this.price = 20000.0;
      }
      case 2 -> {
       this.dificultLevel = 3;
       this.deadline = 40;
       this.price = 40000.0;
      }
     }

     for (int i = 0 ; i < 1*this.dificultLevel; i++)
     {
     generator.dice = generator.rollDice(6);

      for(int j = 0 ; j< 20; j++)
       this.pointsToDo[generator.dice] +=1;
     }

     this.name = generator.nameGenerator("src/project/PojectName");

    }

    public void projectInfo()
    {

        System.out.println(this.name);
        for(int i = 0 ; i < 6 ; i++) {
            if(pointsToDo[i]>0) {
                switch(i) {
                    case 0:   System.out.println("backend:" + this.pointsToDo[0]);
                        break;
                    case 1:   System.out.println("frontend:" + this.pointsToDo[1]);
                        break;
                    case 2:   System.out.println("bazy danych:" + this.pointsToDo[2]);
                        break;
                    case 3:   System.out.println("aplikacje mobilne:" + this.pointsToDo[3]);
                        break;
                    case 4:   System.out.println("WordPress:" + this.pointsToDo[4]);
                        break;
                    case 5:   System.out.println("prestashop:" + this.pointsToDo[5]);
                        break;
                }
            }
        }
     System.out.println("poziom zaawansowania:" + this.dificultLevel);
    }

}


