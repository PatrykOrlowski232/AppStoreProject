package player;

import humans.Employers;
import game.Game;

import java.util.Scanner;

public class Player {
    public String name;
    public Employers[] employers = new Employers[15];

    public int numberOfEmployers;
    public Double cash;
    int points;

    public Game game;
    public int noOfProgramist;

    public int zusConfession;

    Scanner scanner = new Scanner(System.in);

    public Player(){
        System.out.println("Podaj imię gracza:");
        this.name = scanner.nextLine();
        this.cash = 10000.0;
        this.points = 0;
        this.numberOfEmployers = 0;

    }

    public void addEmployer() {

        for(int i=0; i<5;i++)
        {
            game.avaibleEmployers[i].programistInfo();
        }




    }



  public   void goToZUS(){
        zusConfession++;
        game.turns++;



    }

    }


