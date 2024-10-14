package mortalkombat;

public class Debuff extends Action {

    @Override
    public String getType() {
        return "Debuff";
    }

    @Override
    public void realisation(EnemyFabric fighter1, EnemyFabric fighter2, String fighter2ActionType) {
        switch (fighter2ActionType) {
            case "Hit" -> {
            }
            case "Block" -> {
                if (Math.random()<0.75){
                    fighter2.setMovesWithDebuff(fighter1.getLevel());
                }
            }
            case "Debuff" -> {
            }
            case "Heal" -> {
            }
        }
    }
}