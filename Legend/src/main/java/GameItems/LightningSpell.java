package GameItems;

public class LightningSpell extends Magic {

    public LightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
    }

    @Override
    public Number effectDamage(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectDefense(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectDog(Number oldNumber) {
        return oldNumber.doubleValue()*0.9;
    }
    @Override
    public String toString() {
        return "\033[0;35m" + super.toString() + "\033[0;37m";
    }
    
}
