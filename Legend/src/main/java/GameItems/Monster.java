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

public class Monster implements Organism, Model, GameEntity {
    NumberProperty hp;
    NumberProperty level;
    ListProperty effectsList;
    public StringProperty heroName;
    BooleanProperty alive;
    NumberProperty damage;
    NumberProperty defense;
    StringProperty heroType;
    NumberProperty dodge;
    public static final String WARRIOR_STRING = "\033[0;31m Dragons  \033[0;37m";
    public static final String SORCERER_STRING = "\033[0;32m Exoskeletons  \033[0;37m";
    public static final String PALADINS_STRING = "\033[0;34m Spirits  \033[0;37m";

    public Monster(String name, int level, int damage,int defense,int dodge,String type) {
        hp = new NumberProperty(100*level);
        heroName = new StringProperty(name);
        List<GameEffects> status = new ArrayList<GameEffects>();
        effectsList = new ListProperty(status);
        alive = new BooleanProperty(true);
        heroType = new StringProperty(type);
        this.level = new NumberProperty(level);
        this.damage = new NumberProperty(){
          @Override
          public Iterator<String> get() {
              ArrayList<String> dam = new ArrayList<>();
              dam.add(getValue().toString());
              return dam.iterator();
          }  
          @Override
              public Number getValue() {
                  return this.value;
              }
        };
        this.damage.set(damage);
        this.defense = new NumberProperty(){
          @Override
          public Iterator<String> get() {
              ArrayList<String> def = new ArrayList<>();
              def.add(getValue().toString());
              return def.iterator();
          }  
          @Override
              public Number getValue() {
                  return this.value;
              }
        };
        this.defense.set(defense);
        this.dodge = new NumberProperty(){
          @Override
          public Iterator<String> get() {
              ArrayList<String> dod = new ArrayList<>();
              dod.add(getValue().toString());
              return dod.iterator();
          }  
          @Override
              public Number getValue() {
                  return this.value;
              }
        };
        this.dodge.set(dodge);
    }
    @Override
    public int attack(Organism target) {
        int damage = target.beAttacted(this);
        this.checkAlive();
        return damage;

    }

    @Override
    public int beAttacted(Organism source) {
        int sourceDamage = source.getDamage();
        Random random = new Random();
        int r = random.nextInt(100);
        if(r<this.dodge.getValue().intValue()){
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
    public int getLevel() {
        return level.getValue().intValue();
    }

    @Override
    public void addExp(int exp) {

    }

    @Override
    public int getDamage() {
        double dam = damage.getValue().doubleValue();
          for(Object e : effectsList){
              GameEffects effect = (GameEffects)e;
              dam = effect.effectDamage(dam).intValue();
          }
        return (int)dam;
    }


    @Override
    public void use(Useable item) {
        
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

    }

    @Override
    public void setMoney(int newMoney) {

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
        double def = defense.getValue().doubleValue();
          for(Object e : effectsList){
              GameEffects effect = (GameEffects)e;
              def = effect.effectDefense(def).intValue();
          }
        return (int) def;
    }

    @Override
    public int getDodge() {
        double dod = dodge.getValue().doubleValue();
          for(Object e : effectsList){
              GameEffects effect = (GameEffects)e;
              dod = effect.effectDog(dod).intValue();
          }
        return (int)dod;
    }

    @Override
    public void setDodge(Number newdod) {
        this.dodge.set(newdod);

    }

    @Override
    public void putInBag(Buyable item) {

    }

    @Override
    public boolean sellItem(Buyable item) {
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
        return "" + heroName + "/" + level + "/" + damage +"/" + defense +"/" + dodge + "Type: " + heroType + ",health:" +  getHp() ;
    }
    private void checkAlive(){
        if(getHp()<=0){
            this.alive.set("False");
        }
    }

    @Override
    public int getMp() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getStrength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDexterity() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAgility() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMoney() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getExp() {
        // TODO Auto-generated method stub
        return 0;
    }
}
