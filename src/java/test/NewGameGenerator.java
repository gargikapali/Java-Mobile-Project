/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Arrays;
import javax.ejb.Stateless;
import model.CardSet;
import model.Game;

/**
 *
 * @author Su-Ting Chen
 */
@Stateless
public class NewGameGenerator {
    

    public Game generateNewGame(){
        
        CardSet cardset = new CardSet();
        
        
        //set the answer
        String[] answer;
        answer = cardset.getCardSet();      
        Game newGame = new Game();
        newGame.setAnswer(Arrays.toString(answer));
        
        
        
        //set the flipped
        String[] f = new String[Globals.numOfPairs*2];
        for(int i = 0 ; i < f.length ; i++){
            f[i] = "0";
        }
        newGame.setFlipped(Arrays.toString(f));
        
        return newGame;
    }
    
}
