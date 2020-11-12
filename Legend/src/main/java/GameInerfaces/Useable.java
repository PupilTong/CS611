package GameInerfaces;
/**
 * useable item for limited time use, like potions
 * gives forever effect
 */
public interface Useable {
    void use(Organism user);
    boolean isuseable();
    Number addHp(Number oldNumber);
    Number addMp(Number oldNumber);
    Number addDamage(Number oldNumber);
    Number addDefense(Number oldNumber);
    Number addDog(Number oldNumber);
    Number addStrength(Number oldNumber);
    Number addAgility(Number oldNumber);
    Number addDexterity(Number oldNumber);
    Number addExp(Number oldNumber);

}
