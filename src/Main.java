import humans.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Programist p1 = new Programist();

        p1.sick();

        System.out.println(p1.position);
        System.out.println("Zarobki " +p1.price);
        System.out.println("Czy na L4 " +p1.isSick);
        System.out.println(p1.name);



    }


}