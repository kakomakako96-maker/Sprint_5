import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LionTest {

    @Mock
    Predator predator;
    private Lion lion;

    @BeforeEach
    void newLion() throws Exception {
        lion = new Lion(predator, "Самец");
    }

    @Test
     void constructorSexTrue() {
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
        Mockito.when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        List<String> actual = lion.getFood();
        Assertions.assertEquals(expected, actual, "Ошибка! У хищника не может быть такого питания");
    }
    @Test
    void getKittensReturnLionKittens(){
        Mockito.when(predator.getKittens(3)).thenReturn(3);
        int actual = lion.getKittens(3);
        int expected = 3;
        Assertions.assertEquals(expected, actual, "Ошибка! Передано неверное количество котят");
    }
}
//Класс с параметризованными тестами
class LionParametersTest {

    @Mock
    Predator predator;

    private AutoCloseable mockCloseable;

    @BeforeEach
    void initMocks() {
        mockCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockCloseable.close();
    }

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
                Arguments.of(8, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("countKittens")
    void getKittensReturnLionKittens(int count, int expected) throws Exception {
        Lion lion = new Lion(predator, "Самец");
        Mockito.when(predator.getKittens(count)).thenReturn(count);
        int actual = lion.getKittens(count);
        Assertions.assertEquals(expected, actual, "Ошибка! Передано неверное количество котят");
    }
}