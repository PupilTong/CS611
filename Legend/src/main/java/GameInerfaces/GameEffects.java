package GameInerfaces;

public interface GameEffects {
    Number effectHealHp(Number oldNumber);
    Number effectHealMp(Number oldNumber);
    Number effectDamage(Number oldNumber);
    Number effectDefense(Number oldNumber);
    Number effectDog(Number oldNumber);
    Number effectStrength(Number oldNumber);
    Number effectAgility(Number oldNumber);
    Number effectDexterity(Number oldNumber);
    Number effectAddExp(Number oldNumber);
    void newRound();
    int getLife();
    public static final String Type_Magic = "magic";
    public static final String Type_Weapon = "weapon";
    public static final String Type_armor = "armor";
}
