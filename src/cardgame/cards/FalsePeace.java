
package cardgame.cards;

import cardgame.*;
import cardgame.target.TargetManager;

public class FalsePeace implements Card {
    private class FalsePeaceEffect extends AbstractCardEffectTarget{

        FalsePeaceEffect(Player p, Card c) { 
            super(p,c);
        }
                
        @Override
        public void resolve() {
            ((Player)targets.get(0).getTarget()).setPhase(Phases.COMBAT, new SkipPhase(Phases.COMBAT));
        }
        
        @Override
        public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[FALSE PEACE] No target avaiable.");
                return false;
            }
        }
        
        @Override
        public void setTarget() throws Exception {
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.PLAYER_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
        
    }
    @Override
    public Effect getEffect(Player owner) {
        return new FalsePeaceEffect(owner, this);
    }

    @Override
    public String name() {
        return "False Peace";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player skips his next combat phase.";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
