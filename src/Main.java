import game.Game;
import humans.*;
import player.Player;
import project.Project;

import javax.sound.midi.SysexMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {


   Game game = new Game();
   Scanner scanner = new Scanner(System.in);

        int turn = 0;
   while(true) {


       game.drawGame(turn);
       scanner.nextLine();
       game.players[turn].addEmployer();
       game.players[turn].cash +=1000.0;

       turn++;
       if(turn == game.numbersOfPlayers) {
           turn = 0;
           game.nextDay(turn);
       }

   }











    }



        }

