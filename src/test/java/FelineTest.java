import com.example.Feline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class FelineTest {

    private static Stream<Arguments> kittensCount() {
        return Stream.of(
                Arguments.of(2, 2),
                Arguments.of(1, 1),
                Arguments.of(5, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("kittensCount")
     void getKittensCount(int count, int expected) {
        Feline feline = new Feline();
        int actual = feline.getKittens(count);
        Assertions.assertEquals(expected, actual, "Ошибка! Передано неверное количество котят");
    }

    @Test
     void getKittensReturnsOne() {
        Feline feline = new Feline();
        int expected = 1;
        int actual = feline.getKittens();
        Assertions.assertEquals(expected, actual, "Ошибка! Ожидается, что будет 1 котенок");
    }

    @Test
     void getFamilyReturnCatFamilyString() {
        Feline feline = new Feline();
        String expected = "Кошачьи";
        String actual = feline.getFamily();
        Assertions.assertEquals(expected, actual, "Ожидалось семейство " + "'" + expected + "'" + ", но получено " + "'" + actual + "'");
    }
}