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
                System.out.println("DEBUFF CANCELED!: " + fighter2.getName() + " HIT FOR : " + (-fighter2.getDamage() * 1.15));
                fighter1.setHealth((int) (-fighter2.getDamage() * 1.15) + fighter1.getHealth());
            }
            case "Block" -> {
                System.out.println("DEBUFF APPLIED!");
                fighter2.setMovesWithDebuff(fighter1.getLevel() + 2);
            }
            case "Debuff" -> {
            }
            case "Heal" -> {
            }
        }
    }
}