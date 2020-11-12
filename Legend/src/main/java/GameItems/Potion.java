package GameItems;

import java.util.HashMap;

import GameInerfaces.Buyable;
import GameInerfaces.GameEffects;
import GameInerfaces.Organism;
import GameInerfaces.Useable;

public class Potion implements Buyable, Useable {

    boolean used = false;
    int cost;
    int requiredLevel;
    public HashMap<String,Integer> attribute = new HashMap<>();
    private String name;
    public static final String health = "\033[0;32mhealth\033[0;37m";
    public static final String mana = "\033[0;34mmana\033[0;37m";
    public static final String strength = "\033[0;31mstrength\033[0;37m";
    public static final String agility = "\033[0;35magility\033[0;37m";
    public static final String dexterity = "\033[0;33mdexterity\033[0;37m";
    public static final String defense = "defense";
    @Override
    public void use(Organism user) {
        user.use(this);
        used = true;
    }

    @Override
    public boolean isuseable() {
        return !used;
    }

    @Override
    public Number addHp(Number oldNumber) {
        if(this.attribute.containsKey(Potion.health)){
            return oldNumber=oldNumber.intValue() + attribute.get(Potion.health);
        }
        return oldNumber;
        
    }

    @Override
    public Number addMp(Number oldNumber) {
        if(this.attribute.containsKey(Potion.mana)){
            return oldNumber=oldNumber.intValue() + attribute.get(Potion.health);
        }
        return oldNumber;
    }

    @Override
    public Number addDamage(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addDefense(Number oldNumber) {
        if(this.attribute.containsKey(Potion.defense)){
            return oldNumber=oldNumber.intValue() + attribute.get(Potion.defense);
        }
        return oldNumber;
    }

    @Override
    public Number addDog(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number addStrength(Number oldNumber) {
        if(this.attribute.containsKey(Potion.strength)){
            return oldNumber=oldNumber.intValue() + attribute.get(Potion.health);
        }
        return oldNumber;
    }

    @Override
    public Number addAgility(Number oldNumber) {
        if(this.attribute.containsKey(Potion.agility)){
            return oldNumber=oldNumber.intValue() + attribute.get(Potion.health);
        }
        return oldNumber;
    }

    @Override
    public Number addDexterity(Number oldNumber) {
        if(this.attribute.containsKey(Potion.dexterity)){
            return oldNumber=oldNumber.intValue() + attribute.get(Potion.health);
        }
        return oldNumber;
    }

    @Override
    public Number addExp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public boolean onBuy(Organism o) {
        if(o.getMoney()>this.cost&&o.getLevel()>=this.requiredLevel) {
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
    public Potion(String name,int cost,int requiredLevel){
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }
    @Override
    public String toString() {
        String ret = name + "/"+cost + "/" + requiredLevel + "/";
        for(String s : this.attribute.keySet()){
            ret += s + ":" + attribute.get(s) +",";
        }
        return ret;
    }
}
