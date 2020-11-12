package GameInerfaces;


public interface Organism {
    int getDamage();
    /**
     * attack someone
     * @param target
     * @return damage
     */
    int attack(Organism target);
    /**
     * be attacked by some one
     * @param target
     * @return hp loss
     */
    int beAttacted(Organism source);
    /**
     * equip/replace sth
     * @param item
     */
    void equip(GameEffects item);
    /**
     * unequip sth
     * @param item
     */
    void unequip(GameEffects item);
    /**
     * get health
     * @return
     */
    int getHp();
    void setHp(Number newhp);
    int healHp(Number value);
    /**
     * get mana
     * @return
     */
    int getMp();
    void setMp(Number newMp);
    int healMp(Number value);
    int getDefense();
    int getDodge();
    void setDodge(Number newdod);
    /**
     * get level
     * @return
     */
    int getLevel();
    /**
     * get strgenth 
     * @return
     */
    int getStrength();
    /**
     * get dexterity
     * @return
     */
    int getDexterity();
    /**
     * get agility
     * @return
     */
    int getAgility();
    /**
     * get money
     * @return
     */
    int getMoney();
    /**
     * get experience level
     * @return
     */
    int getExp();

    /**
     * add exp
     * @param exp
     */
    void addExp(int exp);
    void use(Useable item);
    boolean isalive();
    void setMoney(int newMoney);
    void putInBag(Buyable item);
    /**
     * sell an item
     * @param item to be selled
     * @return if item is selled sucessfully.
     */
    boolean sellItem(Buyable item);
}
