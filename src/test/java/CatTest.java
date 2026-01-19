import com.example.Cat;
import com.example.Feline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;


public class CatTest {

    @Mock
    Cat cat;

    @BeforeEach
    public void createOutputFile() {
        Feline feline = new Feline();
        cat = new Cat(feline);
    }

    @Test
    public void getSoundReturnMeow() {
        String expected = "Мяу";
        String actual = cat.getSound();
        Assertions.assertEquals(expected, actual, "Ошибка! Животное не говорит Мяу");
    }

    @Test
    public void getFoodReturnFood() throws Exception {
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = cat.getFood();
        Assertions.assertEquals(expected, actual, "Ошибка! У хищника не может быть такого питания");
    }
}
