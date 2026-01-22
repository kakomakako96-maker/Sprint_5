import com.example.Cat;
import com.example.Feline;

import com.example.Predator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CatTest {

    @Mock
    Predator predator;
    private Cat cat;

    @BeforeEach
    void newCat() {
        cat = new Cat(predator);
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
        Mockito.when(cat.getFood()).thenReturn(expected);
        List<String> actual = cat.getFood();
        Assertions.assertEquals(expected, actual, "Ошибка! У хищника не может быть такого питания");
    }
}
