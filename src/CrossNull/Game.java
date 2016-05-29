import java.lang.reflect.Type;
import java.util.Scanner;

/**
 * Created by nikolaeva.ekaterina on 19.05.2016.
 */
public enum Game implements Player {
    HumanVSHuman(0), HumanVSAi(1),AiVSAi(2);

    Game gameType;

    private int idi;

    Game(int id)   {
        this.idi = id;
    }

    public int getId (){
        return idi;
    }



    public void TypeofGame (int idi){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите тип игры :  " +
                "0 - Человек против человека, 1 - Человек против Компьютера, 2- Компьютер против Компьютера ");
        int id = sc.nextInt();
        gameType = Game.values()[id];
        System.out.println("Выбран тип игры: " + id);

    }
   public  void NewGame (){
       if ( gameType == HumanVSHuman)

    {
        System.out.println("uu");
    }

}





    @Override
    public char getChar() {
        return 0;
    }

    @Override
    public int getNextX() {
        return 0;
    }

    @Override
    public int getNextY() {
        return 0;
    }

    @Override
    public Point2d getNextStep() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setChar(char aChar) {

    }
}
