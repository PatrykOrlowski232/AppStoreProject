package humans;


import java.io.IOException;

public class Client extends Human {

     public String type;
   public int paymentsDelay;
  public  int punishOfDelay;
  public  int notEndProject;

    public Client() throws IOException {

        name = generator.nameGenerator("src/humans/HumanName");

        generator.dice = generator.rollDice(2);

        switch (generator.dice) {
            case 0 -> {
               type = "luzak";
               paymentsDelay = 30;
               punishOfDelay = 20;
               notEndProject = 0;
            }
            case 1 -> {
                type = "wymagajacy";
                paymentsDelay = 0;
                punishOfDelay = 0;
                notEndProject = 50;
            }
            case 3 -> {
                type = "skrwl";
                paymentsDelay = 30;
                punishOfDelay = 0;
                notEndProject = 100;
            }
        }


    }


}
