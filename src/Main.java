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


   while(true) {


       game.drawGame(game.turns);

       if(game.turns == game.numbersOfPlayers) {
           game.turns = 0;
           game.nextDay();
       }

   }











    }



        }

