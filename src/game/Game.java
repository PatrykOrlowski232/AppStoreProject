package game;


import humans.Employers;
import project.Project;
import player.Player;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;


public class Game {


    public Employers[] avaibleEmployers;
    public Project[] avaibleProject;

    public int numbersOfPlayers;

    int numberOfAvaibleProject;
   public int numberOfAvailbleEmployers;

    public Player[] players;


    public int turns;

    public LocalDate date2
            = LocalDate.of(2020, 1, 1);

    Scanner scanner = new Scanner(System.in);


    public Game() throws IOException, IOException {
        turns = 0;

        numberOfAvailbleEmployers = 5;
        numberOfAvaibleProject = 5;

        System.out.println("Podaj liczbę graczy");
        numbersOfPlayers = scanner.nextInt();

        players = new Player[numbersOfPlayers];
        avaibleEmployers = new Employers[numberOfAvailbleEmployers];
        avaibleProject = new Project[numberOfAvaibleProject];

        for (int indexOfPlayer = 0; indexOfPlayer < numbersOfPlayers; indexOfPlayer++) {
            players[indexOfPlayer] = new Player();
            players[indexOfPlayer].game = this;

        }

        for (int index = 0; index < numberOfAvailbleEmployers; index++) {
            avaibleEmployers[index] = new Employers();
            avaibleProject[index] = new Project();
        }


    }


    public void drawGame(int turn) throws IOException {


        System.out.println("Tura gracza: " + players[turn].name + " Stan konta: " + players[turn].cash + " Data: " + date2);
        System.out.println("Co chcesz teraz zrobić?:");
        System.out.println("1.Rozejrzeć się za nowymi Projektami:");
        System.out.println("2.Rozejrzeć się za nowymi pracownikami:");
        System.out.println("3.Odwiedzić Zus:");
        System.out.println("4.Zwolnić pracownika:");
        System.out.println("5.Testować projekt:");
        System.out.println("6.Pracować nad projektem:");
        System.out.println("7. Zleć zadanie podwykonawcy:");
        System.out.println("8. Przyjąć projekt:");
        System.out.println("9. Przydziel pracowników do projektu:");
        System.out.println("10. Pokaż swoje projekty:");
        System.out.println("11. Pokaż swoich pracowników:");

        players[turn].PlayersChoice();

    }




    private void checkNewMonth() {
        if (date2.getDayOfMonth() == 1) {
            for (int i = 0; i < numbersOfPlayers; i++)
                if (players[i].zusConfession < 2) {
                    System.out.println("Gracza " + players[i].name + " dojeżdża ZUS i US");


                }

        }
    }

    public boolean checkIsWorkDay(){
        if(date2.getDayOfWeek() != DayOfWeek.SATURDAY && date2.getDayOfWeek() != DayOfWeek.SUNDAY)
        return true;
        else
            return false;
    }

    void checkProject(Project pro){
        int check = 0;
        for (int i = 0 ; i < 6 ; i++)
        {
            if(pro.pointsToDo[i] > 0)
                check++;
        }
        if(check == 0 && pro.done == false){ pro.done = true; pro.timeofpayot = date2.plusDays(10);
        };
    }
    public   void work(Employers emp) {

        if (emp.project != null) {

            if (emp.sick() == false && checkIsWorkDay() == true) {
                System.out.println("LALAL");
                 for (int i = 0; i < 6; i++) {
                    if (emp.project.pointsToDo[i] != 0 && emp.skillTechnology[i] != 0) {
                        emp.project.pointsToDo[i] -= emp.skillTechnology[i];
                        break;
                    }
                }
            }

        }
    }
public void nextDay(){
            for(int i = 0 ; i < numbersOfPlayers ; i++)
            {
                for (int j = 0 ; j < players[i].numberOfEmployers ; j++) {
                    work(players[i].employers[j]);
                }
                }

    for(int i = 0 ; i < numbersOfPlayers ; i++)
    {
        for (int j = 0 ; j < players[i].numberOfProjects ; j++) {
            checkProject(players[i].projects[j]);
            if(players[i].projects[j].done == true && players[i].projects[j].timeofpayot == date2  )
                players[i].cash += players[i].projects[j].price;

        }
    }



    date2 = date2.plusDays(1);

           checkNewMonth();
}


           }




