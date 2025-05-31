package mortalkombat;

public class Heal extends Action {

    @Override
    public String getType() {
        return "Heal";
    }

    @Override
    public void realisation(EnemyFabric fighter1, EnemyFabric fighter2, String fighter2ActionType) {
        switch (fighter2ActionType) {
            case "Hit" -> {
            }
            case "Block" -> {
                fighter1.setHealth((fighter1.getMaxHealth() - fighter1.getHealth()) / 3 + fighter1.getHealth());
            }
            case "Debuff" -> {
            }
            case "Heal" -> {
            }
        }
    }
}