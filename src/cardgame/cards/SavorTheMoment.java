
package cardgame.cards;

import cardgame.*;

public class SavorTheMoment implements Card {
    private class SavorTheMomentEffect extends AbstractCardEffect{

        SavorTheMomentEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve(){
            CardGame.instance.getCurrentPlayer().setPhase(Phases.END,new SkipPhase(Phases.END));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.NULL,new SkipPhase(Phases.NULL));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.DRAW, new DefaultDrawPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.UNTAP,new SkipPhase(Phases.UNTAP));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.COMBAT, new DefaultCombatPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.MAIN, new DefaultMainPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.END, new DefaultEndPhase());
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new SavorTheMomentEffect(owner, this);
    }

    @Override
    public String name() {
        return "Savor the moment";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Take an extra turn after this one. Skip the untap step of that turn.";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
}
