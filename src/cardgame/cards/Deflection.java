/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffectTarget;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.PermanentTarget;
import cardgame.Player;
import cardgame.Target;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author giaco
 */
public class Deflection implements Card{
    
    private class DeflectionEffect extends AbstractCardEffectTarget {
        //costruttore
        AbstractCardEffectTarget effectTarget;
        public DeflectionEffect(Player p, Card c) { 
            super(p,c);
            effectTarget = null;
        }

        @Override
        public boolean play() {
            setTarget();
            return super.play();
        }
       
        @Override
        public void setTarget(){
            Scanner input = new Scanner(System.in);
            int choice;
            ArrayList<AbstractCardEffectTarget> effects = CardGame.instance.getStack().getALSingleTargets();
            if(effects.size()>0){
                do{
                    int i = 1;
                    System.out.println( "[DEFLECTION] SELECT TARGET (from effect stack):");
                    for(AbstractCardEffectTarget e : effects){
                        System.out.println(i+". "+e.getCard().toString()+" [TARGET: "+e.toString()+"]");
                        i++;
                    }
                    choice =  input.nextInt();
                }while(choice < 0 && choice > effects.size());
                effectTarget = effects.get(choice-1);
            }
            else{
                System.out.println("[DEFLECTION] Nothing to select... PASS");
            }
        }
        @Override
        public void resolve() {
            if(effectTarget!=null)
                effectTarget.setTarget();
        }
        
    }
    @Override
    public Effect getEffect(Player owner) {
        //get effect ritorna un nuovo effetto -- 
        return new DeflectionEffect(owner, this);  
    }

    @Override
    public String name() {
       return "Deflection";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Change the target of target spell with a single target.";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return true;
    }
    
}
