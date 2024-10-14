package mortalkombat;

public abstract class Action {
    public abstract String getType();
    public abstract void realisation(EnemyFabric fighter1, EnemyFabric fighter2, String fighter2ActionType);
}
