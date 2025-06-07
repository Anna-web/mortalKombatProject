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
                fighter1.setHealth((int) (-fighter2.getDamage() * 1.15) + fighter1.getHealth());
            }
            case "Block" -> {
                fighter2.setMovesWithDebuff(fighter1.getLevel() + 1);
            }
            case "Debuff" -> {
            }
            case "Heal" -> {
            }
        }
    }
}