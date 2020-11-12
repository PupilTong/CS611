package GameItems;

import GameInerfaces.Organism;
import GameInerfaces.Useable;

public class EveryRoundRegain implements Useable {

    @Override
    public void use(Organism user) {
        
        user.setHp(((int)((double)user.getHp()*1.1)));
        user.setMp(((int)((double)user.getMp()*1.1)));

    }

    @Override
    public boolean isuseable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Number addHp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addMp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addDamage(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addDefense(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addDog(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addStrength(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addAgility(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addDexterity(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addExp(Number oldNumber) {
        return oldNumber;
    }
    
}
