package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Generator {




    public int dice;


    Random generator = new Random();



    public int rollDice(int top) {    //metoda losująca liczbę "kostka"

        dice = generator.nextInt(top);


        return (dice);

    }

    public String nameGenerator(String filePath) throws FileNotFoundException {

        File file = new File(filePath);  //Załadownie pliku z imionami
        Scanner in = new Scanner(file);

        dice = rollDice(44);
        String name = null;

        for (int i = 0; i < dice; i++)  //wylosowanie imienia pracownika za pomoca kostki
            name = in.nextLine();


        return (name);
    }




}








