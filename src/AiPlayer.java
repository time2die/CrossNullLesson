/**
 * Created by time2die on 10.05.2016.
 */
public abstract class AiPlayer implements Player {
    @Override
    public String getName() {
        return "Ai";
    }

    public abstract void setChar(char aChar);
}
