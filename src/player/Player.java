package player;

import humans.Employers;
import game.Game;
import project.Project;

import java.io.IOException;
import java.util.Scanner;

public class Player {
    public String name;
    public Employers[] employers = new Employers[15];
    public Project[] projects = new Project[15];

    public int numberOfEmployers;
    public int numberOfProjects;
    public Double cash;
    int points;

    private String playersChoice;
    public Game game;


    public int zusConfession;

    Scanner scanner = new Scanner(System.in);

    public Player(){
        System.out.println("Podaj imię gracza:");
        this.name = scanner.nextLine();
        this.cash = 100000.0;
        this.points = 0;
        this.numberOfEmployers = 0;
        this.numberOfProjects = 0;

    }

    public void PlayersChoice() throws IOException {

        playersChoice = scanner.next();

        switch(playersChoice) {
            case"1":addProject();
                game.turns++;
            break;


            case"2":addEmployer();
                game.turns++;
                break;


            case"3":goToZUS();
                game.turns++;
                break;

            case"4":layOffEmployer();
                break;

            case"5":System.out.println("blablabla");
                break;

            case"6":work();
                game.turns++;
                break;


            case"7":subcontractor();
                break;

            case"9":assignedEmployerToProject();
                break;
            case"10":showYourProjects();
            scanner.nextInt();
                break;

            case"11":showYourEmployers();
                scanner.nextInt();
                break;

        }
        }


    public void addEmployer() throws IOException {

        for(int i=0; i<game.numberOfAvailbleEmployers;i++)
        {
            System.out.println(i + ". ");
            game.avaibleEmployers[i].programistInfo();
            System.out.println(" ");
        }

        System.out.println("Kogo czesz zatrudnić?");

        int x = scanner.nextInt();

        if(cash >= game.avaibleEmployers[x].price)
        {
            employers[numberOfEmployers] = game.avaibleEmployers[x];
            game.avaibleEmployers[x] = new Employers();
            System.out.println("Gratulacje zatrudnileś "+employers[numberOfEmployers].name);
            cash -= employers[numberOfEmployers].price;
            numberOfEmployers++;

        }
        else
            System.out.println("Nie stać Cie na tego pracownika");

    }

    void assignedEmployerToProject(){
        showYourProjects();
        System.out.println("Do którego projektu chcesz przydzielić pracownika");
        int x = scanner.nextInt();
        int z = 0;
        System.out.println("Do tego projektu możesz przydzielić następujących pracowników:");
        for(int j = 0 ; j < numberOfEmployers ; j++) {
            for (int i = 0; i < 6; i++) {
                if (projects[x].pointsToDo[i] > 0 && employers[j].skillTechnology[i] > 0) {
                    System.out.println(j + ". ");
                    employers[j].programistInfo();
                    z++;
                    break;
                }
            }

        }
        if(z > 0) {
            System.out.println("Kogo wybierasz?:");
            int y = scanner.nextInt();
            employers[y].project = projects[x];
        }
        else {
            System.out.println("Nie masz odpowiednich pracowników do tego projektu");
            System.out.println("Wciśnij dowolną cyfrę aby kontynuować");
            scanner.nextInt();

        }
    }

    void layOffEmployer()
    {
        showYourEmployers();
        System.out.println("Kogo chcesz zwolnić");
        int x = scanner.nextInt();
        employers[x] = null;

        for(int i = x+1 ; i > numberOfEmployers ; i++) {
            if(employers[i] != null) {
                employers[i - 1] = employers[i];
                employers[i] = null;
            }
        }
        numberOfEmployers--;

    }

    public void showYourEmployers()
    {

        for(int i=0; i<numberOfEmployers;i++)
        {

            System.out.println(i + ". ");
            employers[i].programistInfo();
            System.out.println(" ");
        }
        System.out.println("By przejść dalej wciśnij dowolną cyfrę");
    }

    public void showYourProjects()
    {

        for(int i=0; i<numberOfProjects;i++)
        {
            System.out.println(i + ". ");
            projects[i].projectInfo();
            System.out.println("Do kiedy termin: " + projects[i].date);
            System.out.println(" ");
        }
        System.out.println("By przejść dalej wciśnij dowolną cyfrę");
    }

    public void addProject() throws IOException {

        for(int i=0; i<game.numberOfAvailbleEmployers;i++)
        {
            System.out.println(i + ". ");
            game.avaibleProject[i].projectInfo();
            System.out.println(" ");
        }

        System.out.println("Jaki projekt chcesz przyjąć?");

        int x = scanner.nextInt();


            projects[numberOfProjects] = game.avaibleProject[x];

            projects[numberOfProjects].date = game.date2.plusDays(projects[numberOfProjects].deadline);

            game.avaibleProject[x] = new Project();
            System.out.println("Gratulacje przyjąłeś projekt "+projects[numberOfProjects].name);
            numberOfProjects++;




    }

    void subcontractor(){
    showYourProjects();
    System.out.println("Który projekt chcesz zlecić podwykonawcy");
    int x = scanner.nextInt();
        System.out.println("Którego podwykonawce wybierasz");
        System.out.println("1. Dobry , bierzę 70% zapłaty , ale wszystko będzie dobrze ");
        System.out.println("2. Średni , bierzę 50% zapłaty , ale jest droba szansa , że coś pójdzie nie tak");
        System.out.println("3. Słaby , bierze 30% zapłaty , ale jest duża szansa , że coś pójdzie nie tak ");
    }

  public   void goToZUS(){
        zusConfession++;
        game.turns++;
      System.out.println("Odwiedziłeś ZUS");

    }

    void work(){
        showYourProjects();
        System.out.println("Nad którym projektem , chcesz pracować");
        int x = scanner.nextInt();
        for(int i = 0 ; i <6 ; i++)
        {
            projects[x].pointsToDo[i] -=1;
        }
    }

    }


