package game;


import generator.Generator;
import humans.Client;
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

    public Client[] avaibleClient;

    Generator generator = new Generator();



    public int numbersOfPlayers;

  public  int numberOfAvaibleProject;
   public int numberOfAvailbleEmployers;

    public Player[] players;


    public int turns;

    public LocalDate dayOfGame
            = LocalDate.of(2020, 1, 1);

    Scanner scanner = new Scanner(System.in);


    public Game() throws IOException, IOException {
        turns = 0;

        numberOfAvailbleEmployers = 5;
        numberOfAvaibleProject = 3;


        System.out.println("Podaj liczbę graczy");
        numbersOfPlayers = scanner.nextInt();

        players = new Player[numbersOfPlayers];
        avaibleEmployers = new Employers[50];
        avaibleProject = new Project[50];
        avaibleClient = new Client[20];

        for (int i = 0; i < 20; i++) {
            avaibleClient[i] = new Client();
        }

        for (int indexOfPlayer = 0; indexOfPlayer < numbersOfPlayers; indexOfPlayer++) {
            players[indexOfPlayer] = new Player();
            players[indexOfPlayer].game = this;

        }



        for (int index = 0; index < 50; index++) {
            avaibleEmployers[index] = new Employers();

            avaibleProject[index] = new Project(avaibleClient[generator.rollDice(20)]);


        }

    }


    public void drawGame(int turn) throws IOException {


        System.out.println("Tura gracza: " + players[turn].name + " Punkty:" + players[turn].points + " Stan konta: " + players[turn].cash + " Data: " + dayOfGame.getDayOfWeek() +" "
                + dayOfGame + " Miesieczne Koszta: " + players[turn].calculateValue() +
                "Podatek do zapłacenia w tym miesiącu: " + (players[turn].cashBrutto * 0.1));
        System.out.println("Co chcesz teraz zrobić?:");
        System.out.println("1.Rozejrzeć się za nowymi Projektami:");
        System.out.println("2.Rozejrzeć się za nowymi pracownikami:");
        System.out.println("3.Odwiedzić Zus:");
        System.out.println("4.Zwolnić pracownika:");
        System.out.println("5.Testować projekt:");
        System.out.println("6.Pracować nad projektem:");
        System.out.println("7. Zleć zadanie podwykonawcy:");
        System.out.println("8. Oddaj projekt:");
        System.out.println("9. Przydziel pracowników do projektu:");
        System.out.println("10. Pokaż swoje projekty:");
        System.out.println("11. Pokaż swoich pracowników:");

        players[turn].PlayersChoice();

    }



    private void checkNewMonth() {
        if (dayOfGame.getDayOfMonth() == 1) {
            for (int i = 0; i < numbersOfPlayers; i++) {

                players[i].cash -= players[i].calculateValue();
                players[i].cash -= (players[i].cashBrutto * 0.1);
                players[i].cashBrutto = 0;

                if (players[i].zusConfession < 2) {
                    System.out.println("Gracza " + players[i].name + " dojeżdża ZUS i US");
                    deletePlayer(i);
                }
            }

        }
    }

    public void deletePlayer(int x) {
        System.out.println("Gracz " + players[x].name + " przegrał");
        scanner.nextInt();
        players[x] = null;

        for (int i = x; i < numbersOfPlayers; i++) {
            if (players[i] != null) {
                players[i - 1] = players[i];
                players[i] = null;
            }


        }
        numbersOfPlayers--;
    }




    public boolean checkIsWorkDay(){
        if(dayOfGame.getDayOfWeek() != DayOfWeek.SATURDAY && dayOfGame.getDayOfWeek() != DayOfWeek.SUNDAY)
        return true;
        else
            return false;
    }



    void checkProject(Project project , int whoesPlayer){
        int check = 0;
        for (int i = 0 ; i < 6 ; i++)
        {
            if(project.pointsToDo[i] > 0)
                check++;
        }
        if(check == 0 && project.done == false){ project.done = true;  // zaliczenie projektu
        }

        if(project.dayToDeadline.equals(dayOfGame) && project.done == false)  // sprawdzenie terminu projektu
        { if(project.client.type.equals("luzak") && generator.rollDice(100) < 20 ) {
            System.out.println("Klient " + project.client.name + "Mówi: Luuuuuz nie ma problemu , że się spóźnisz");
            scanner.nextInt();
        }
            else
            players[whoesPlayer].cash -= project.deadlinepunish;
        }

    }
    public   void work(Employers emp , int whoesPlayer) {

        if (emp.project != null || emp.position.equals("marketingowiec")) {

            if (emp.sick() == false && checkIsWorkDay() == true) {


                if(emp.position.equals("tester"))
                {
                    if(players[whoesPlayer].countOfTester * 3 >= (players[whoesPlayer].numberOfEmployers - players[whoesPlayer].countOfTester))
                        for(int i = 0 ; i < players[whoesPlayer].numberOfProjects ; i++)
                            players[whoesPlayer].projects[i].isTested = true;

                    }

                if(emp.position.equals("marketingowiec"))
                {
                    players[whoesPlayer].marketPoint ++;
                    if(players[whoesPlayer].marketPoint >= 5) {
                        numberOfAvaibleProject++;
                        players[whoesPlayer].marketPoint = 0;
                    }

                }
                else {

                    for (int i = 0; i < 6; i++) {
                        if (emp.project.pointsToDo[i] != 0 && emp.skillTechnology[i] != 0) {
                            System.out.println("Pracownik gracza " + players[whoesPlayer].name + emp.name + " pracuje nad " + emp.project.name);
                            emp.project.pointsToDo[i] -= emp.skillTechnology[i];
                            break;
                        }
                    }
                }
            }

        }
    }
public void nextDay(){
            for(int i = 0 ; i < numbersOfPlayers ; i++)
            {
                for (int j = 0 ; j < players[i].numberOfEmployers ; j++) {
                    work(players[i].employers[j] , i);
                }
                }

    for(int i = 0 ; i < numbersOfPlayers ; i++)
    {
        if(players[i].cash <= 0)
            deletePlayer(i);

        if(players[i].numberOfProjects > 0) {
            for (int j = 0; j < players[i].numberOfProjects; j++) {
                checkProject(players[i].projects[j], i);
                if(players[i].projects[j].itsLets == true) {
                    if (players[i].projects[j].done == true && players[i].projects[j].timeofpayot.equals(dayOfGame)) {
                        if (players[i].projects[j].client.equals("skrwl") && generator.rollDice(100) <= 1) {
                            System.out.println("Tępy **** " + players[i].projects[j].client.name + " mówi: Dzięki za projekt ale Ci nie zaplace");
                            scanner.nextInt();
                            players[i].deleteProject(i);
                        } else {

                            players[i].cash += players[i].projects[j].price;
                            players[i].cashBrutto += players[i].projects[j].price;
                            players[i].deleteProject(i);
                        }
                    }
                }

            }
        }
    }



    dayOfGame = dayOfGame.plusDays(1);

           checkNewMonth();
}


           }




