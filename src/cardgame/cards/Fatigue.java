
package cardgame.cards;

import cardgame.*;
import cardgame.target.TargetManager;

public class Fatigue implements Card{
    private class FatigueEffect extends AbstractCardEffectTarget{

        FatigueEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {            
            ((Player)targets.get(0).getTarget()).setPhase(Phases.DRAW, new SkipPhase(Phases.DRAW));
        }

        @Override
        public void setTarget() throws Exception{
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.PLAYER_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
        @Override
        public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[FATIGUE] No target avaiable.");
                return false;
            }
        }
    }
    
    @Override
    public Effect getEffect(Player owner) {
        return new FatigueEffect(owner, this);
    }

    @Override
    public String name() {
        return "Fatigue";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player skips his next draw phase.";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
}
