package game;


import humans.Employers;
import project.Project;
import player.Player;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


public class Game {



   public Employers[] avaibleEmployers;
   public Project[] avaibleProject ;

    public int numbersOfPlayers;
    public Player[] players;


   public int turns;

   public LocalDate date =  LocalDate.of(2020,1,1);

    Scanner scanner = new Scanner(System.in);


   public Game() throws IOException, IOException {
       turns = 0;

       System.out.println("Podaj liczbę graczy");
       numbersOfPlayers  = scanner.nextInt();

       players = new Player[numbersOfPlayers];
       avaibleEmployers = new Employers[5];
       avaibleProject = new Project[5];

       for(int indexOfPlayer=0 ; indexOfPlayer < numbersOfPlayers;indexOfPlayer++)
       {
           players[indexOfPlayer] = new Player();
           players[indexOfPlayer].game = this;

       }

       for(int index = 0 ; index < 5 ; index++)
       {
           avaibleEmployers[index] = new Employers();
           avaibleProject[index] = new Project();
       }



   }


public void drawGame(int turn){

    System.out.println("Tura gracza: " + players[turn].name +" Stan konta: " + players[turn].cash +" Data: " + date);
    System.out.println("Co chcesz teraz zrobić?:");
    System.out.println("1.Rozejrzeć się za nowymi klientami:");
    System.out.println("2.Rozejrzeć się za nowymi pracownikami:");
    System.out.println("3.Odwiedzić Zus:");
    System.out.println("4.Zwolnić pracownika:");
    System.out.println("5.Testować projekt:");
    System.out.println("6.Pracować nad projektem:");
    System.out.println("7. Zleć zadanie podwykonawcy:");
    System.out.println("8. Przyjąć projekt:");

}


public void nextDay(int turn){
           date = date.plusDays(1);
           if(date.getDayOfMonth() == 1) {
               if (players[turn].zusConfession < 2) {
                   System.out.println("Gracza " + players[turn].name + " dojeżdża ZUS i US");



               }
           }


}
}
