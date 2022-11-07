package humans;

import generator.Generator;
import project.Project;

import java.io.FileNotFoundException;
import java.io.IOException;


public  class Employers extends Human {

    public int[] skillTechnology = new int[6];
    public String position;

    public int skillPoint;

    public int failChance;

    private Project project;

    boolean knowledge;


    Generator generator = new Generator();

    public Employers() throws FileNotFoundException , IOException {//konstruktor obiektu programista

        generator.dice = generator.rollDice(4);

        switch (generator.dice) {   //przy pomocy rzutu kostką , losowany jest stopień zaawansowania nowego pracownika , oraz pieniądze potrzebne na jego utrzymanie
            case 0 -> {
                this.position = "stażysta";
                this.price = 2500.0;
                this.skillPoint = 2;
                failChance = 70;
            }
            case 1 -> {
                this.position = "junior";
                this.price = 5000.0;
                this.skillPoint = 4;
                failChance = 50;
            }
            case 2 -> {
                this.position = "mid";
                this.price = 7500.0;
                this.skillPoint = 6;
                failChance = 30;
            }
            case 3 -> {
                this.position = "senior";
                this.price = 10000.0;
                this.skillPoint = 8;
                failChance = 10;
            }
        }


        for (int i = skillPoint; i > 0; i--) { // rozdanie punktow stazowych w poszczegolne technologie , za pomoca rzutu kostka
            generator.dice = generator.rollDice(6);
            switch (generator.dice) {
                case 0 -> this.skillTechnology[0] += 1; //front
                case 1 -> this.skillTechnology[1] += 1; //back
                case 2 -> this.skillTechnology[2] += 1; //db
                case 3 -> this.skillTechnology[3] += 1; //mobile
                case 4 -> this.skillTechnology[4] += 1; // wordpress
                case 5 -> this.skillTechnology[5] += 1; // presta
            }


            this.name = generator.nameGenerator("src/humans/HumanName");

            this.isSick = false;


        }

    }

    public void programistInfo()
    {
        System.out.println(this.name);
        System.out.println("poziom zaawansowania:" + this.position);
        System.out.println("Wynagrodzenie:" + this.price);


        for(int i = 0 ; i < 6 ; i++) {
            if(skillTechnology[i]>0) {
                switch(i) {
                    case 0:   System.out.println("backend:" + this.skillTechnology[0]);
                    break;
                    case 1:   System.out.println("frontend:" + this.skillTechnology[1]);
                    break;
                    case 2:   System.out.println("bazy danych:" + this.skillTechnology[2]);
                    break;
                    case 3:   System.out.println("aplikacje mobilne:" + this.skillTechnology[3]);
                    break;
                    case 4:   System.out.println("WordPress:" + this.skillTechnology[4]);
                    break;
                    case 5:   System.out.println("prestashop:" + this.skillTechnology[5]);
                    break;
                                         }
                                         }
                                         }

    }

  public   void work(){


      if(this.sick() == false) {
          for (int i = 0; i < 6; i++) {
              if (this.project.pointsToDo[i] != 0 && this.skillTechnology[i] != 0) {
                  this.project.pointsToDo[i] -= this.skillTechnology[i];

                  break;
              }
          }
      }else
          System.out.println("Pracownik jest chory");

        }


    public void projectSetteer(Project project){

         knowledge = false;

            for (int i = 0; i < 6; i++)
                if (project.pointsToDo[i] > 0 && this.skillTechnology[i] > 0) {
                    this.project = project;
                    knowledge = true;
                    break;
                }
            if (knowledge == false)
                System.out.println("Pracownik nie ma odpowiednych umiejętności do pracy nad tym projektem");

    }
}