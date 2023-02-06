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

boolean i = true;

   while(true) {

       if(game.numbersOfPlayers == 0) {

           System.out.println("KONIEC GRY");
           break;
       }

       if(game.players[game.turns].points >  2) {

           System.out.println("Wygrał gracz " + game.players[game.turns].name);
           break;
       }


        game.drawGame(game.turns);


        if (game.turns == game.numbersOfPlayers) {
            game.turns = 0;
            game.nextDay();

        }



   }



        System.out.println("XD");







    }



        }

