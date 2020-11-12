package GameItems;

import GameInerfaces.Buyable;
import GameInerfaces.GameEffects;
import GameInerfaces.Organism;
import MVVM.*;

public class Armor extends StringProperty implements Buyable, GameEffects {
    int requireLevel;
    int damageReduction;
    int cost;
    String name;
    @Override
    public Number effectHealHp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectHealMp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectDamage(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectDefense(Number oldNumber) {
        return oldNumber.intValue() + this.damageReduction;
    }

    @Override
    public Number effectDog(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectStrength(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectAgility(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectDexterity(Number oldNumber) {
        return null;
    }

    @Override
    public Number effectAddExp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public void newRound() {

    }

    @Override
    public int getLife() {
        return 100;
    }

    @Override
    public boolean onBuy(Organism o) {
        if(o.getMoney()>this.cost&&o.getLevel()>=this.requireLevel){
            o.setMoney(o.getMoney() - this.cost);
            return true;
        }
        return false;
    }

    @Override
    public boolean onSell(Organism o) {
        o.setMoney(o.getMoney() + this.cost/2);
        return true;
    }

    @Override
    public int getBuyPrice() {
        return this.cost;
    }

    @Override
    public int getSellPrice() {
        return this.cost/2;
    }
    public Armor(String name,int cost,int requiredLevel,int damageReduction){
        this.name = name;
        this.cost = cost;
        this.requireLevel = requiredLevel;
        this.damageReduction = damageReduction;
    }
    @Override
    public String toString() {
        return name +"/"+ cost +"/" +requireLevel + "/" +damageReduction;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().isInstance(Armor.class) || obj.getClass().isAssignableFrom(Armor.class)){
            return true;
        }
        return false;
    }
}
