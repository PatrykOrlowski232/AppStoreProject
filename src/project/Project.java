package project;
import generator.Generator;
import humans.Client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class Project {

    public Double price;
    public LocalDate dayToDeadline;
   public  String name;
    public Boolean done;
   public  int[] pointsToDo = new int[6];
    public long deadline;

    public boolean itsLets;

    public boolean isTested;

    public double deadlinepunish;

    public boolean playerWorked;

    public Client client;
   public  LocalDate timeofpayot;

   String testedAnswer;

  public  int dificultLevel;


    Generator generator = new Generator();
    public Project(Client client) throws FileNotFoundException , IOException {
     this.client = client;
     generator.dice = generator.rollDice(3);
     done = false;
     isTested = false;
     playerWorked = false;
     itsLets = false;



     switch (generator.dice) {
      case 0 -> {
      dificultLevel = 1;
       deadline = 20;
       price = 10000.0;
       deadlinepunish = price * 0.3 ;
      }
      case 1 -> {
       dificultLevel = 2;
       deadline = 30;
       price = 20000.0;
      }
      case 2 -> {
       dificultLevel = 3;
       deadline = 40;
       price = 40000.0;
       deadlinepunish = price * 0.4 ;
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



        System.out.println(name);

        System.out.println("Klient:" + client.name);

        if(isTested == true) testedAnswer = "Tak";
        else testedAnswer = "Nie";
        if(done == true) {
            System.out.println("Czas do otrzymania zapłaty " + timeofpayot);
            System.out.println("Testowany: " + testedAnswer);

        }
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
     System.out.println("poziom zaawansowania:" + dificultLevel);
     System.out.println("Kara za przekroczenie terminu:" + deadlinepunish);
     System.out.println("Zapłata :" + price);
    }

}


