package GameItems;

public class IceSpell extends Magic {

    public IceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
    }

    @Override
    public Number effectDamage(Number oldNumber) {
        return oldNumber.doubleValue()*0.9;
    }

    @Override
    public Number effectDefense(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectDog(Number oldNumber) {
        return oldNumber;
    }
    @Override
    public String toString() {
        return "\033[0;34m" + super.toString() + "\033[0;37m";
    }
    
}
