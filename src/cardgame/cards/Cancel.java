
package cardgame.cards;

import cardgame.*;
import cardgame.target.TargetManager;

public class Cancel implements Card {

    @Override
    public Effect getEffect(Player owner) {
        return new CancelEffect(owner, this);
    }
    
    private class CancelEffect extends AbstractCardEffectTarget {

        public CancelEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[CANCEL] No target avaiable.");
                return false;
            }
        }
        
        @Override
        public void setTarget() throws Exception{
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.STACK_TARGETSPELL_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }

        @Override
        public void resolve() {
            CardGame.instance.getStack().remove((Effect)targets.get(0).getTarget());
        }
        
    }    

        
    @Override
    public String name() {
       return "Cancel";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Counter target spell.";
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
