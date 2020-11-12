package GameItems;

import GameInerfaces.Buyable;
import GameInerfaces.GameEffects;
import GameInerfaces.Organism;

public abstract class Magic implements Buyable, GameEffects {

    protected String name;
    protected int cost;
    protected int requiredLevel;
    protected int damage;
    protected int manaCost;
    Magic(String name,int cost,int requiredLevel,int damage,int manaCost){
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damage = damage;
        this.manaCost = manaCost;
    }
    @Override
    public Number effectHealHp(Number oldNumber) {
        return oldNumber;
    }

    @Override
    public Number effectHealMp(Number oldNumber) {
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
        return oldNumber;
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
    public boolean useMagic(Organism source,Organism target){
        if(source.getMp()>=manaCost){
            target.setHp(target.getHp() - damage - (source.getDexterity()*damage/10000));
            source.healMp(-manaCost);
            target.equip(this);
        }
        return false;
    }
    @Override
    public String toString() {
        return name +"/" +cost + "/" + requiredLevel +"/" +damage + "/" + manaCost;
    }
    
}
