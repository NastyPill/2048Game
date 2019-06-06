import com.game.twentyfoureight.game.Cell;
import com.game.twentyfoureight.game.Field;
import com.game.twentyfoureight.game.Game;
import org.junit.Assert;
import org.junit.Test;

public class Test2048 {
    @Test
    public void test() {
        Cell[][] field =
                {{new Cell(4), new Cell(2), new Cell(8), new Cell(16)},
        {new Cell(2), new Cell(8), new Cell(32), new Cell(8)},
        {new Cell(16), new Cell(128), new Cell(64), new Cell(2)},
        {new Cell(4), new Cell(8), new Cell(2), new Cell(4)}};
        Assert.assertEquals(true, new Game(new Field(field), 0).gameOver());
    }

}
