package player;

import generator.Generator;
import humans.Employers;
import game.Game;
import project.Project;

import java.io.IOException;
import java.util.Scanner;

public class Player {

    Generator generator = new Generator();
    public String name;
    public Employers[] employers = new Employers[15];
    public Project[] projects = new Project[15];

    public int numberOfEmployers;
    public int numberOfProjects;
    public double cash;

    public double cashBrutto;
   public int points;

    double monthlyValues;

    private String playersChoice;

    public int marketPoint;

    public int countOfTester;
    public Game game;




    public int zusConfession;

    Scanner scanner = new Scanner(System.in);

    public Player(){
        System.out.println("Podaj imię gracza:");
        name = scanner.nextLine();
        cash = 100000.0;
        numberOfEmployers = 0;
        numberOfProjects = 0;
        points = 0;

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

            case"5":test();
                game.turns++;
                break;

            case"6":work();
                game.turns++;
                break;


            case"7":subcontractor();
                break;

            case"8":letProject();
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

        if(x < game.numberOfAvailbleEmployers) {
            if (cash >= game.avaibleEmployers[x].price) {
                employers[numberOfEmployers] = game.avaibleEmployers[x];
                game.avaibleEmployers[x] = new Employers();
                System.out.println("Gratulacje zatrudnileś " + employers[numberOfEmployers].name);

                if (employers[numberOfEmployers].position.equals("tester"))
                    countOfTester++;

                cash -= employers[numberOfEmployers].price;
                numberOfEmployers++;

            } else
                System.out.println("Nie stać Cie na tego pracownika");
        }
        else
            addEmployer();

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
        if(numberOfEmployers == 0) {
            System.out.println("BRAK PRACOWNIKÓW");
            scanner.nextInt();
        }
        else {
            showYourEmployers();
            System.out.println("Kogo chcesz zwolnić");
            int x = scanner.nextInt();

            if(employers[x].position == "tester")
                countOfTester--;

            employers[x] = null;

            for (int i = x; i < numberOfEmployers; i++) {
                if (employers[i] != null) {
                    employers[i - 1] = employers[i];
                    employers[i] = null;
                }
            }

            numberOfEmployers--;
        }


    }

    public void showYourEmployers()
    {
        if(numberOfEmployers == 0)
        {
            System.out.println("BRAK PRACOWNIKÓW");
            System.out.println("By przejść dalej wciśnij dowolną cyfrę");
            scanner.nextInt();
        }
        else {
            for (int i = 0; i < numberOfEmployers; i++) {

                System.out.println(i + ". ");
                employers[i].programistInfo();
                System.out.println(" ");
            }
        }
        System.out.println("By przejść dalej wciśnij dowolną cyfrę");
    }

    public void showYourProjects()
    {
        if(numberOfProjects == 0)
        {
            System.out.println("BRAK PROJEKTÓW");
            System.out.println("By przejść dalej wciśnij dowolną cyfrę");
            scanner.nextInt();
        }
        else {

            for (int i = 0; i < numberOfProjects; i++) {
                System.out.println(i + ". ");
                projects[i].projectInfo();
                System.out.println("Do kiedy termin: " + projects[i].dayToDeadline);
                System.out.println(" ");
            }

            System.out.println("By przejść dalej wciśnij dowolną cyfrę");
        }
    }

    public void addProject() throws IOException {

        for(int i=0; i<game.numberOfAvaibleProject;i++)
        {
            System.out.println(i + ". ");
            game.avaibleProject[i].projectInfo();
            System.out.println(" ");
        }

        System.out.println("Jaki projekt chcesz przyjąć?");

        int x = scanner.nextInt();

        if(x < game.numberOfAvaibleProject) {
            projects[numberOfProjects] = game.avaibleProject[x];

            projects[numberOfProjects].dayToDeadline = game.dayOfGame.plusDays(projects[numberOfProjects].deadline);

            game.avaibleProject[x] = new Project(game.avaibleClient[generator.rollDice(20)]);
            game.avaibleProject[x].client = game.avaibleClient[generator.rollDice(20)];
            System.out.println("Gratulacje przyjąłeś projekt " + projects[numberOfProjects].name);
            numberOfProjects++;
        }
        else addProject();



    }

    public void deleteProject(int x) {
        projects[x] = null;

        for (int i = x; i > numberOfProjects; i++) {
            if (projects[i] != null) {
                projects[i - 1] = projects[i];
                projects[i] = null;
            }
            numberOfProjects--;

        }
    }

    void subcontractor(){
    showYourProjects();
    System.out.println("Który projekt chcesz zlecić podwykonawcy");
    int x = scanner.nextInt();
        System.out.println("Którego podwykonawce wybierasz");
        System.out.println("1. Dobry , bierzę 70% zapłaty , ale wszystko będzie dobrze ");
        System.out.println("2. Średni , bierzę 50% zapłaty , ale jest droba szansa , że coś pójdzie nie tak");
        System.out.println("3. Słaby , bierze 30% zapłaty , ale jest duża szansa , że coś pójdzie nie tak ");
        scanner.nextInt();
    }

  public   void goToZUS(){
        if(game.checkIsWorkDay() == true) {
            zusConfession++;
            System.out.println("Odwiedziłeś ZUS");
            scanner.nextInt();
        }
        else{
            System.out.println("ZUS zamknięty , wróć w dzień roboczy");
            scanner.nextInt();
        }



    }

    void letProject(){

        int y = 0;

        for(int j = 0 ; j<numberOfProjects;j++)
            if(projects[j].done == true)
                y++;

        if(y > 0) {
            System.out.println("Który projekt chcesz oddać");

            for (int i = 0; i < numberOfProjects; i++) {

                if (projects[i].done == true) {
                    System.out.println(i + ". ");
                    projects[i].projectInfo();
                }


            }

            int x = scanner.nextInt();

            projects[x].timeofpayot = game.dayOfGame;

            projects[x].timeofpayot.plusDays(paymentsTime(projects[x]));

            projects[x].itsLets = true;

            if(projects[x].dificultLevel > 2 && projects[x].playerWorked == false)
                points++;
        }
        else
            System.out.println("Brak projektów do oddania");
        scanner.nextInt();
    }

    void work(){
        showYourProjects();
        if(numberOfProjects == 0) {
            System.out.println("Brak projektów do pracy");
            scanner.nextInt();
        }
                    else {
            System.out.println("Nad którym projektem , chcesz pracować");
            int x = scanner.nextInt();
            projects[x].playerWorked = true;
            for (int i = 0; i < 6; i++) {
                projects[x].pointsToDo[i] -= 1;
            }
        }
    }


    public double calculateValue(){
        monthlyValues = 0 ;
        for(int i = 0 ; i < numberOfEmployers ; i++)
        {
            monthlyValues += employers[i].price;
        }
        return monthlyValues;
    }

    long paymentsTime(Project pro) {
        long payments = 10;

        if(pro.client.type.equals("luzak") && generator.rollDice(100) <= 30)
        {
            payments+= 7;

            System.out.println("Klient " + pro.client.name + " Mówi: Nie obrazisz się jak zapłace Ci tydzień później?");
            scanner.nextInt();
        }

        if(pro.client.type.equals("skrwl") && generator.rollDice(100) <= 30)
        {
            payments+= 7;

            System.out.println("Tępy **** " + pro.client.name + " Mówi: Zapłacę tydzień później");
            scanner.nextInt();
        }


        if(pro.client.type.equals("skrwl") && generator.rollDice(100) <= 5)
        {
            payments+= 30;

            System.out.println("Tępy **** " + pro.client.name + " Mówi: W tym miesiącu nie mam pieniędzy");
            scanner.nextInt();
        }


        return payments;
    }

    void test(){
        System.out.println("Który projekt chcesz testować");
        for(int i = 0 ; i < numberOfProjects ; i++)
            if(projects[i].isTested == false)
                projects[i].projectInfo();
        int x = scanner.nextInt();

        projects[x].isTested = true;
        projects[x].playerWorked = true;
    }

    }


