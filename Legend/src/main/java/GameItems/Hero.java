package GameItems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import GameEngine.Game;
import GameEngine.GameEntity;
import GameInerfaces.Buyable;
import GameInerfaces.GameEffects;
import GameInerfaces.Organism;
import GameInerfaces.Useable;
import MVVM.BooleanProperty;
import MVVM.ListProperty;
import MVVM.Model;
import MVVM.NumberProperty;
import MVVM.StringProperty;

public abstract class Hero implements Organism, Model, GameEntity {
    NumberProperty mp;
    NumberProperty hp;
    NumberProperty level;
    NumberProperty exp;
    NumberProperty money;
    NumberProperty strength;
    NumberProperty agility;
    NumberProperty dexterity;
    ListProperty effectsList;
    public StringProperty heroName;
    BooleanProperty alive;
    StringProperty heroType;
    public ArrayList<Buyable> bag;
    NumberProperty damage;
    NumberProperty defense;
    public static final String WARRIOR_STRING = "\033[0;31m warrior \033[0;37m";
    public static final String SORCERER_STRING = "\033[0;32m sorcerer \033[0;37m";
    public static final String PALADINS_STRING = "\033[0;34m paladins \033[0;37m";

    public Hero(String name, int startmana, int sstrgenth, int sagility, int sdexterity, int smoney, int startexp,String type) {
        hp = new NumberProperty(100);
        mp = new NumberProperty(startmana);
        heroName = new StringProperty(name);
        exp = new NumberProperty(startexp);
        money = new NumberProperty(smoney);
        strength = new NumberProperty(sstrgenth);
        agility = new NumberProperty(sagility);
        dexterity = new NumberProperty(smoney);
        List<GameEffects> status = new ArrayList<GameEffects>();
        effectsList = new ListProperty(status);
        alive = new BooleanProperty(true);
        heroType = new StringProperty(type);
        this.bag = new ArrayList<>();
        level = new NumberProperty(1);
        this.damage = new NumberProperty(){
          @Override
          public Iterator<String> get() {
              ArrayList<String> dam = new ArrayList<>();
              dam.add(getValue().toString());
              return dam.iterator();
          }  
          @Override
              public Number getValue() {
                  double dam = strength.getValue().intValue()*5/100;
                    for(Object e : effectsList){
                        GameEffects effect = (GameEffects)e;
                        dam = effect.effectDamage(dam).intValue();
                    }
                  return dam;
              }
        };
        this.defense = new NumberProperty(){
          @Override
          public Iterator<String> get() {
              ArrayList<String> def = new ArrayList<>();
              def.add(getValue().toString());
              return def.iterator();
          }  
          @Override
              public Number getValue() {
                  int def = 0;
                    for(Object e : effectsList){
                        GameEffects effect = (GameEffects)e;
                        def = effect.effectDamage(def).intValue();
                    }
                  return def;
              }
        };
    }

    private void levelup(){
        //reset hp
        level.set(level.getValue().intValue() + 1);
        hp.set(level.getValue().intValue()*100);
        //reset mp
        mp.set((int)(mp.getValue().doubleValue()*1.1));
        //grow
        this.strength.set(this.strength.getValue().doubleValue()*1.05);
        this.agility.set(this.agility.getValue().doubleValue()*1.05);
        this.dexterity.set(this.dexterity.getValue().doubleValue()*1.05);
        if(this.heroType.get().next().equals(Hero.WARRIOR_STRING)){
            this.strength.set(this.strength.getValue().doubleValue()*1.05);
            this.agility.set(this.agility.getValue().doubleValue()*1.05);
        }
        else if(this.heroType.get().next().equals(Hero.SORCERER_STRING)){
            this.agility.set(this.agility.getValue().doubleValue()*1.05);
            this.dexterity.set(this.dexterity.getValue().doubleValue()*1.05);
        }
        else if(this.heroType.get().next().equals(Hero.PALADINS_STRING)){
            this.strength.set(this.strength.getValue().doubleValue()*1.05);
            this.dexterity.set(this.dexterity.getValue().doubleValue()*1.05);
        }
    }
    @Override
    public int attack(Organism target) {
        int damage = target.beAttacted(this);
        return damage;

    }

    @Override
    public int beAttacted(Organism source) {
        int sourceDamage = source.getDamage();
        Random random = new Random();
        int r = random.nextInt(100);
        if(r<(int)(agility.getValue().doubleValue()*0.2)){//100*0.002
            //miss
            return 0;
        }
        else{
            sourceDamage -= getDefense()*5/100;
            if(sourceDamage<0)sourceDamage = 0;

            int hp_after_attacked = hp.getValue().intValue() - sourceDamage;
            hp.set(hp_after_attacked);
            this.checkAlive();
            return sourceDamage;
        }


    }

    @Override
    public void equip(GameEffects item) {
        for(Object e : this.effectsList){
            GameEffects effect = (GameEffects)e;
            if(effect.equals(item)){
                this.effectsList.getList().remove(e);
            }
        }

    }


    @Override
    public void unequip(GameEffects item) {
        for(Object e : this.effectsList){
            GameEffects effect = (GameEffects)e;
            if(effect.equals(item)){
                this.effectsList.getList().remove(e);
            }
        }
    }

    @Override
    public int getHp() {
        return this.hp.getValue().intValue();
    }

    @Override
    public int getMp() {
        return this.mp.getValue().intValue();
    }

    @Override
    public int getLevel() {
        return level.getValue().intValue();
    }

    @Override
    public int getStrength() {
        return strength.getValue().intValue();
    }

    @Override
    public int getDexterity() {
        return dexterity.getValue().intValue();
    }

    @Override
    public int getAgility() {
        return agility.getValue().intValue();
    }

    @Override
    public int getMoney() {
        return this.money.getValue().intValue();
    }

    @Override
    public int getExp() {
        return exp.getValue().intValue();
    }

    @Override
    public void addExp(int exp) {
        for(Object e : this.effectsList){
            GameEffects effect = (GameEffects)e;
            exp = effect.effectAddExp(exp).intValue();
        }
        int currentExp = getExp();
        currentExp += exp;
        if(currentExp>=getLevel()*10){
            currentExp -= getLevel()*10;
            levelup();
        }
        this.exp.set(currentExp);

    }

    @Override
    public int getDamage() {
        return this.damage.getValue().intValue();
    }


    @Override
    public void use(Useable item) {
        
        this.setHp(item.addHp(this.hp.getValue()));
        this.setMp(item.addMp(this.hp.getValue()));
        //this.basicDamage.set(item.addDamage(this.basicDamage.getValue()));
        //this.basicDefense.set(item.addDefense(this.basicDefense.getValue()));
        this.strength.set(item.addStrength(this.strength.getValue()));
        this.agility.set(item.addAgility(this.agility.getValue()));
        this.dexterity.set(item.addDexterity(this.dexterity.getValue()));
        this.exp.set(item.addExp(this.exp.getValue()));
    }
    
    public boolean isalive() {
        return this.alive.getValue();
    }

    @Override
    public void setHp(Number newhp) {
        this.hp.set(newhp);
        this.checkAlive();

    }

    @Override
    public void setMp(Number newMp) {
        this.mp.set(newMp);

    }

    @Override
    public void setMoney(int newMoney) {
        this.money.set(newMoney);

    }


    @Override
    public int healHp(Number value) {
        for(Object e : this.effectsList){
            GameEffects effect = (GameEffects)e;
            value = effect.effectHealHp(value);
        }
        this.setHp(value);
        return value.intValue();
    }

    @Override
    public int healMp(Number value) {
        for(Object e : this.effectsList){
            GameEffects effect = (GameEffects)e;
            value = effect.effectHealMp(value);
        }
        this.setMp(value);
        return value.intValue();
    }

    @Override
    public int getDefense() {
        return this.defense.getValue().intValue();
    }

    @Override
    public int getDodge() {
        //hero has no extra dodge
        return 0;
    }

    @Override
    public void setDodge(Number newdod) {
        //not allowed

    }

    @Override
    public void putInBag(Buyable item) {
        bag.add(item);

    }

    @Override
    public boolean sellItem(Buyable item) {
        for(Buyable i:bag){
            if(i.getClass().isInstance(Buyable.class) || i.getClass().isAssignableFrom(Buyable.class)){
                Buyable buyablei= (Buyable)i;
                if(i.equals(item)){
                    item.onSell(this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onNewRound() {
        for(Object e : this.effectsList){
            GameEffects effect = (GameEffects)e;
            effect.newRound();
            if(effect.getLife()==0){
                effectsList.getList().remove(effect);
            }
        }

    }
    @Override
    public String toString() {
        return "" + heroName + "/" + mp + "/" + strength +"/" + agility +"/" + dexterity + "/" + money + "/" + exp + ",health:" +  getHp() ;
    }
    private void checkAlive(){
        if(getHp()<=0){
            this.alive.set("False");
        }
    }
    public void revive(){
        setHp(getLevel()*50);
        alive.set("true");
    }
}
