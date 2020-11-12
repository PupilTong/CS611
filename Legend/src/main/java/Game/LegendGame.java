package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.Position;

import org.w3c.dom.UserDataHandler;

import GameEngine.*;
import GameInerfaces.Buyable;
import GameInerfaces.GameEffects;
import GameInerfaces.Useable;
import GameItems.*;
import MVVM.*;
import Models.*;
import Views.*;

public class LegendGame extends Game<String> {
    public static final int mapHeight = 8;
    public static final int mapWidth = 8;
    public static final int fightChance = 80;
    public static final String resourceDir = "/home/ubuntu/CS611/hw4/Legend/resources/";

    GameEngine gameEngine;
    GameUIComponmentFactory factory;
    public TerminalUIEngine uiEngine;
    Thread mainGame;


    GameUIComponment mapPage;
    GameUIComponment heroSelectPage;
    GameUIComponment mainPage;
    String username;
    ArrayList<Hero> heroteam;

    ArrayList<Armor> armors = new ArrayList<>();
    ArrayList<Weapon> weapons = new ArrayList<>();
    ArrayList<Magic> magics = new ArrayList<>();
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<Potion> potions = new ArrayList<>();

    @Override
    public String getWinner() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void onGameStart() {
        try {
            GameEngine.setRecourseDirectory(LegendGame.resourceDir);
            factory = new GameUIComponmentFactory();
            gameEngine = new GameEngine();
            gameEngine.initialUIEngine(System.in, System.out);
            uiEngine = (TerminalUIEngine) gameEngine.getUiEngine();

            MapView mapView = new MapView();
            MapPageModel mapPageModel = new MapPageModel();
            mapPage = factory.generateCUIcomponment(mapPageModel, mapView, null, gameEngine);

            MainPageModel mainPageModel = new MainPageModel();
            MainView mainView = new MainView();
            mainPage = factory.generateCUIcomponment(mainPageModel, mainView, null, gameEngine);


            ///select hero page

            ArrayList<Warrior> warriors = new ArrayList<>();
            ArrayList<Sorcerer> sorcerers = new ArrayList<>();
            ArrayList<Palandin> paladins = new ArrayList<>();
            //initial hero list.
            File file = new File(GameEngine.resourcesDir + "./entitiesinfo/Warriors.txt");
            Scanner s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<7)break;
                warriors.add(
                    new Warrior(
                        args[0],
                        Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), 
                        Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6])
                        )
                );
            }
            s.close();
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Sorcerers.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<7)break;
                sorcerers.add(
                    new Sorcerer(
                        args[0],
                        Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), 
                        Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6])
                        )
                );
            }
            s.close();
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Paladins.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<7)break;
                paladins.add(
                    new Palandin(
                        args[0],
                        Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), 
                        Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6])
                        )
                );
            }
            s.close();
            HeroSelectView heroSelectView = new HeroSelectView();
            HeroSelectPageModel heroSelectPageModel = new  HeroSelectPageModel(warriors, sorcerers, paladins);
            this.heroSelectPage = factory.generateCUIcomponment(heroSelectPageModel, heroSelectView, null, gameEngine);


            //initial armor list
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Armory.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<4)continue;
                armors.add(
                    new Armor(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])
                        )
                );
            }
            s.close();
            //initial weapon list
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Weaponry.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                weapons.add(
                    new Weapon(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4])
                        )
                );
            }
            s.close();
            //initial Magics
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/FireSpells.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                magics.add(
                    new FireSpell(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4])
                        )
                );
            }
            s.close();
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/IceSpells.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                magics.add(
                    new IceSpell(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4])
                        )
                );
            }
            s.close();
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/LightningSpells.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                magics.add(
                    new LightningSpell(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4])
                        )
                );
            }
            s.close();
            //initial potions
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Potions.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<4)continue;
                Potion newPotion = new Potion(args[0],Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                String[] attributes = args[4].split("/");
                int attributeIncrese = Integer.parseInt(args[3]);
                for(String attribute :attributes){
                    if(attribute.equals("Health"))
                        newPotion.attribute.put(Potion.health, attributeIncrese);
                    else if(attribute.equals("Strength"))
                        newPotion .attribute.put(Potion.strength, attributeIncrese);
                    else if(attribute.equals("Mana"))
                        newPotion .attribute.put(Potion.mana, attributeIncrese);
                    else if(attribute.equals("Agility"))
                        newPotion .attribute.put(Potion.agility, attributeIncrese);
                    else if(attribute.equals("Dexterity"))
                        newPotion .attribute.put(Potion.dexterity, attributeIncrese);
                    else if(attribute.equals("Defense"))
                        newPotion .attribute.put(Potion.defense, attributeIncrese);
                }
                potions.add(newPotion);
            }
            s.close();
            //initMonsters
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Dragons.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                monsters.add(
                    new Monster(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4]),"Dragon"
                        )
                );
            }
            s.close();

            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Exoskeletons.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                monsters.add(
                    new Monster(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4]),"Exoskeleton"
                        )
                );
            }
            s.close();
            file = new File(GameEngine.resourcesDir + "./entitiesinfo/Spirits.txt");
            s = new Scanner(file);
            s.nextLine();//skip title;
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] args = line.split("\\s+");
                if(args.length<5)continue;
                monsters.add(
                    new Monster(
                        args[0],
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Integer.parseInt(args[4]),"Spirit"
                        )
                );
            }
            s.close();

        } catch (Exception e) {
            uiEngine.stop();
            e.printStackTrace();
        }

    }

    @Override
    protected void onGameTie() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onGameHasWinner() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onNextRound() {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() throws Exception {
        this.onGameStart();
        mainPage.show();
        String userString = uiEngine.requestInput("start/quiet?", "s","q");
        if(userString.equals("s")){
            userString = uiEngine.requestInput("input your username");
            this.username = userString;

            //select heros
            HeroSelectPageModel heroSelectPageModel = (HeroSelectPageModel)heroSelectPage.getModel();
            heroteam = new ArrayList<>();
            heroSelectPage.show();
            //worrier
            ArrayList<String> options = new ArrayList<>();
            options.add("s");
            for(int i=0;i<heroSelectPageModel.warList.getList().size();i++){
                options.add(""+i);
            }
            userString = uiEngine.requestInput("select a worrior,use num or s for skip", (String[])options.toArray(new String[0]));
            if(!userString.equals("s")){
                heroteam.add((Hero) heroSelectPageModel.warList.getList().get(Integer.parseInt(userString)));
            }
            //sor
            options.clear();
            options.add("s");
            for(int i=0;i<heroSelectPageModel.sorList.getList().size();i++){
                options.add(""+i);
            }
            userString = uiEngine.requestInput("select a Sorcerer,use num or s for skip", (String[])options.toArray(new String[0]));
            if(!userString.equals("s")){
                heroteam.add((Hero) heroSelectPageModel.sorList.getList().get(Integer.parseInt(userString)));
            }
            //pal
            options.clear();
            if(heroteam.size()!=0){
                options.add("s");
            }
            for(int i=0;i<heroSelectPageModel.palList.getList().size();i++){
                options.add(""+i);
            }
            userString = uiEngine.requestInput("select a Paladin,use num or s for skip", (String[])options.toArray(new String[0]));
            if(!userString.equals("s")){
                heroteam.add((Hero) heroSelectPageModel.palList.getList().get(Integer.parseInt(userString)));
            }
            heroSelectPage.close();
            
            ((MapPageModel)mapPage.getModel()).playername.set(username);
            mapPage.show();
            this.mainGame = new Thread(() -> {
                while (gameTick()) {
                    onNextRound();
                }
                ;
                if (getWinner() == null) {
                    onGameTie();
                } else {
                    onGameHasWinner();
                }
            });
            this.mainGame.start();
        }
        else{

            Runtime.getRuntime().exit(0);
        }
    }

    private boolean gameTick() {
        String userString;
        ArrayList<String> options2 = new ArrayList<>();
        options2.add("i");
        options2.add("q");
        MapPageModel mapPageModel = (MapPageModel)mapPage.getModel();
        userString = uiEngine.requestInput("i-heros Details,m-market,w-move up,s-down,a-left,d-right,q-quit. Allowed:",mapPageModel.addOptions(options2));
        try {
            if(userString.equals("q")){
                Runtime.getRuntime().exit(0);
            }
            else if(userString.equals("i")){
                    int selectedHero = showHeroTeamSelect();
                    showHeroDetails(this.heroteam.get(selectedHero));
            }
            else if(userString.equals("m")){

                Hero selectedHero = heroteam.get(showHeroTeamSelect());
                userString = showMarket(selectedHero,armors,"Armory");
                if(!userString.equals("q"))
                userString = showMarket(selectedHero,weapons,"weaponry");
                if(!userString.equals("q"))
                userString = showMarket(selectedHero,potions,"Poitions");
                if(!userString.equals("q"))
                userString = showMarket(selectedHero,magics,"Magic");
            }
            else{
                int result = mapPageModel.playerMove(userString);
                if(result==-1){
                    //market
                }
                else{
                    if(result<LegendGame.fightChance){
                        FightPageModel fightPageModel = new FightPageModel(heroteam, monsters);
                        FightView fightView = new FightView();
                        UIcomponment fightPage = factory.generateCUIcomponment(fightPageModel, fightView, null, gameEngine);
                        fightPage.show();
                        uiEngine.showMessage("Fight!");

                        EveryRoundRegain everyRoundRegain = new EveryRoundRegain();
                        //hero round
                        boolean heroAlive = true;
                        boolean monsterAlive = true;
                        while(heroAlive&&monsterAlive){
                            heroAlive = false;
                            monsterAlive = false;
                            for(Hero hero:heroteam){
                                for(Object object : fightPageModel.getEnemylist()){
                                    Monster monster = (Monster)object;
                                    if(monster.isalive()){
                                        String uString = uiEngine.requestInput(hero.heroName + " vs " + monster.heroName +". a-attack b-bag(potion/mana)","a","b");
                                        if(uString.equals("a")){
                                            uiEngine.showMessage("attack:" + hero.attack(monster) +"hp");
                                        }
                                        else{
                                            showHeroBag(hero, monster);
                                        }
                                        heroAlive = true;
                                    }
                                }
                            }
                            for(Object object : fightPageModel.getEnemylist()){
                                Monster monster = (Monster)object;
                                for(Hero hero:heroteam){
                                    if(hero.isalive()){
                                        uiEngine.showMessage("attack:" + monster.attack(hero) +"hp");
                                        monsterAlive = true;
                                    }
                                }
                            }

                            for(Hero hero:heroteam){hero.use(everyRoundRegain);}
                        }
                        for(int i=0;i<heroteam.size();i++){
                            Hero hero = heroteam.get(i);
                            Monster monster = (Monster) fightPageModel.getEnemylist().getList().get(i);
                            if(hero.isalive()){
                                hero.addExp(2);
                                hero.setMoney(hero.getMoney()+monster.getLevel()*10);
                            }
                            else{
                                hero.revive();
                            }
                        }
                        fightPage.close();
                        
                    }
                    else{
                        uiEngine.showMessage("UR lucky, no monster");
                    }
                }
            }
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }

    private int showHeroTeamSelect() throws FileNotFoundException {
        ArrayList<String> options2 = new ArrayList<>();
        options2.clear();
        for(int i=0;i<this.heroteam.size();i++){
            options2.add(""+i);
        }
        CurrentHeroSelectModel currentHeroSelectModel = new CurrentHeroSelectModel(heroteam);
        CurrentHeroSelectView cHeroSelectView;
        cHeroSelectView = new CurrentHeroSelectView();
        GameUIComponment selectHero = factory.generateCUIcomponment(currentHeroSelectModel, cHeroSelectView, null, gameEngine);
        selectHero.show();
        String userString = uiEngine.requestInput("select a hero ",(String[])options2.toArray(new String[0]));
        selectHero.close();
        return Integer.parseInt(userString);
    }
    private void showHeroDetails(Hero hero) throws FileNotFoundException {
        HeroDetailPageView heroDetailPageView = new HeroDetailPageView();
        GameUIComponment heroDetailPage = factory.generateCUIcomponment(hero, heroDetailPageView, null, gameEngine);
        heroDetailPage.show();
        String userString = uiEngine.requestInput("q-quit, b-bag", "q","b");
        if(userString.equals("b")){
            showHeroBag(hero,null);
        }
        heroDetailPage.close();
        //quit

    }
    private String showMarket(Hero selectedHero,ArrayList<?> itemList , String name) throws FileNotFoundException {
        MarketPageModel markModel = new MarketPageModel(itemList, name);
        MarketView marketView = new MarketView();
        UIcomponment market = factory.generateCUIcomponment(markModel, marketView, null, gameEngine);
        ArrayList<String> options = new ArrayList<>();
        options.add("s");
        options.add("q");
        market.show();
        markModel.money.set(selectedHero.getMoney());
        String userString = uiEngine.requestInput("buy item: s-skip,q-quit", markModel.addOptions(options));
        while(!(userString.equals("s")||userString.equals("q"))){
            markModel.money.set(selectedHero.getMoney());
            if(markModel.getItem(Integer.parseInt(userString)).getBuyPrice()<=selectedHero.getMoney()){
                Buyable item  =  markModel.getItem(Integer.parseInt(userString));
                try{
                    if(item.onBuy(selectedHero)){
                        selectedHero.putInBag(item);
                        uiEngine.showMessage("bought" + item);
                        markModel.buy(Integer.parseInt(userString));
                    }
                    else{

                        uiEngine.showMessage("illegal buy:" + item);
                    }
                }
                catch(Exception e){
                }
            }
            userString = uiEngine.requestInput("buy item: s-skip,q-quit", markModel.addOptions(options));
        }
        market.close();
        return userString;
    }
    private void showHeroBag(Hero hero,Monster enemy) throws FileNotFoundException {
        BagPageModel bagPageModel = new BagPageModel(hero);
        BagView bagView = new BagView();
        UIcomponment bagPage = factory.generateCUIcomponment(bagPageModel, bagView, null, gameEngine);
        bagPage.show();
        ArrayList<String> options = new ArrayList<>();
        options.add("q");
        String userString = uiEngine.requestInput("q-exit,equip/use:", bagPageModel.addOptions(options));
        if(!userString.equals("q")){
            Object obj = bagPageModel.getItem(Integer.parseInt(userString));
            if(obj.getClass().isInstance(Useable.class) || obj.getClass().isAssignableFrom(Useable.class)){
                Useable useable = (Useable)obj;
                hero.use(useable);
                hero.bag.remove(useable);
            }
            if(obj.getClass().isInstance(GameEffects.class) || obj.getClass().isAssignableFrom(GameEffects.class)){
                if(obj.getClass().isInstance(Magic.class) || obj.getClass().isAssignableFrom(Magic.class)){
                    Magic m = (Magic)obj;
                    if(enemy!=null)
                        m.useMagic(hero, enemy);
                }
                else{
                    hero.equip((GameEffects)obj);
                }
            }
        }
        bagPage.close();
    }
    @Override
    public void stop() {
        try {
            mainGame.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void pause() {
        try {
            mainGame.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void resume() {
        mainGame.notify();

    }

    @Override
    public State getState() {
        return mainGame.getState();
    }
    
}
