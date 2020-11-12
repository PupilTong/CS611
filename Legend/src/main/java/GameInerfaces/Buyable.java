package GameInerfaces;

public interface Buyable {
    boolean onBuy(Organism o);
    boolean onSell(Organism o);
    int getBuyPrice();
    int getSellPrice();
}
