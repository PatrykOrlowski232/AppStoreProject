package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public  class Generator {




    public int dice;

    public int[] skillsPoint = new int[6];


    Random generator = new Random();



    public int rollDice(int top) {    //metoda losująca liczbę "kostka"

        dice = generator.nextInt(top);


        return (dice);

    }

    public String nameGenerator(String filePath) throws FileNotFoundException , IOException {

        File file = new File(filePath);  //Załadownie pliku z nazwami
        Scanner in = new Scanner(file);

         int lineCount = (int) Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).count();
        dice = rollDice(lineCount);

        String name = null;

        for (int i = 0; i < dice; i++)  //wylosowanie imienia bądź nazwy za pomoca kostki
            name = in.nextLine();



        return (name);
    }





}








