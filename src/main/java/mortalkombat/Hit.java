package mortalkombat;

public class Hit extends Action {

    @Override
    public String getType() {
        return "Hit";
    }

    @Override
    public void realisation(EnemyFabric fighter1, EnemyFabric fighter2, String fighter2ActionType) {
        switch (fighter2ActionType) {
            case "Hit" -> {
                if (fighter2.isDebuffed() & fighter1.isDebuffed()) {
                    fighter2.setHealth((int) (-fighter1.getDamage() * 1.25 / 2) + fighter2.getHealth());
                }
                if (fighter2.isDebuffed() & !fighter1.isDebuffed()) {
                    fighter2.setHealth((int) (-fighter1.getDamage() * 1.25) + fighter2.getHealth());
                }
                if (!fighter2.isDebuffed() & fighter1.isDebuffed()) {
                    fighter2.setHealth((int) (-fighter1.getDamage() / 2) + fighter2.getHealth());
                }
                if (!fighter2.isDebuffed() & !fighter1.isDebuffed()) {
                    fighter2.setHealth((int) (-fighter1.getDamage()) + fighter2.getHealth());
                }
            }
            case "Block" -> {
                fighter1.setHealth(-fighter2.getDamage() / 2 + fighter1.getHealth());
            }
            case "Debuff" -> {
                fighter2.setHealth((int) (-fighter1.getDamage() * 1.15) + fighter2.getHealth());
            }
            case "Heal" -> {
                fighter2.setHealth(-fighter1.getDamage() * 2 + fighter2.getHealth());
            }
        }
    }
}