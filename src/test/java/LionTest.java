import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LionTest {

    @Mock
    Predator predator;

    @Test
     void constructorSexTrue() throws Exception {
        Lion lion = new Lion(predator, "Самец");
        Assertions.assertTrue(lion.doesHaveMane(), "Ошибка, при значении Самец hasMane = true");

    }
    @Test
     void constructorSexFalse() throws Exception {
        Lion lion = new Lion(predator, "Самка");
        Assertions.assertFalse(lion.doesHaveMane(), "Ошибка, при значении Самка hasMane = false");
    }

    @Test
    void constructorSexThrows() {
        Exception exception = Assertions.assertThrows(Exception.class,() -> new Lion(predator, "Неизвестно"));
        Assertions.assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage(), "Если не указаны стандартные значение Самец или Самка, то должна появится ошибка");
    }

    @Test
     void getFoodReturnFood() throws Exception {
        Predator predator = new Feline();
        Lion lion = new Lion(predator, "Самец");
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = lion.getFood();
        Assertions.assertEquals(expected, actual, "Ошибка! У хищника не может быть такого питания");
    }
    @Test
    void getKittensReturnLionKittens() throws Exception {
        Predator predator = new Feline();
        Lion lion = new Lion(predator, "Самец");
        int actual = lion.getKittens(3);
        Assertions.assertEquals(3, actual, "Ошибка! Передано неверное количество котят");
    }
}

class LionParametersTest {

    private static Stream<Arguments> constructorSexTest() {
        return Stream.of(
                Arguments.of(null, "Самец", true),
                Arguments.of(null, "Самка", false)
        );
    }

    @ParameterizedTest
    @MethodSource("constructorSexTest")
    void doesHaveManeReturn(Predator predator, String sex, boolean expected) throws Exception {
        Lion lion = new Lion(predator, sex);
        boolean actual = lion.doesHaveMane();
        Assertions.assertEquals(expected, actual, "Ошибка! Переданные значения не совпадают!");
    }

    private static Stream<Arguments> countKittens() {
        return Stream.of(
                Arguments.of(3, "Самец", 3),
                Arguments.of(5, "Самка", 5)
        );
    }

    @ParameterizedTest
    @MethodSource("countKittens")
    void getKittensReturnLionKittens(int count,String sex, int expected) throws Exception {
        Predator predator = new Feline();
        Lion lion = new Lion(predator, sex);
        int actual = lion.getKittens(count);
        Assertions.assertEquals(expected, actual, "Ошибка! Передано неверное количество котят");
    }
}