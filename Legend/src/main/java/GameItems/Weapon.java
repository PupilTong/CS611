package GameItems;

import GameInerfaces.Buyable;
import GameInerfaces.GameEffects;
import GameInerfaces.Organism;
import MVVM.*;

public class Weapon extends StringProperty implements Buyable, GameEffects {
    int requireLevel;
    int damage;
    int cost;
    String name;
    int hands;

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
        return oldNumber.intValue() + this.damage*5/100;
    }

    @Override
    public Number effectDefense(Number oldNumber) {
        return oldNumber.intValue();
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
        if (o.getMoney() > this.cost && o.getLevel() >= this.requireLevel) {
            o.setMoney(o.getMoney() - this.cost);
            return true;
        }
        return false;
    }

    @Override
    public boolean onSell(Organism o) {
        o.setMoney(o.getMoney() + this.cost / 2);
        return true;
    }

    @Override
    public int getBuyPrice() {
        return this.cost;
    }

    @Override
    public int getSellPrice() {
        return this.cost / 2;
    }

    public Weapon(String name, int cost, int requiredLevel, int damage,int hands) {
        this.name = name;
        this.cost = cost;
        this.requireLevel = requiredLevel;
        this.damage = damage;
        this.hands = hands;
    }
    @Override
    public String toString() {
        return name +"/"+ cost +"/" +requireLevel + "/" +damage + "/" + hands;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().isInstance(Armor.class) || obj.getClass().isAssignableFrom(Armor.class)){
            return true;
        }
        return false;
    }
}
