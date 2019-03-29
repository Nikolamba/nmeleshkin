import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DiapasonClass {

    private List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (double i = (double)start; i <= end; i++) {
            result.add(func.apply(i));
            System.out.println(func.apply(i));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Function value linear:");
        new DiapasonClass().diapason(1, 10,
                (input) -> input + 5);
        System.out.println("Function value quadratic:");
        new DiapasonClass().diapason(1, 10,
                (input) -> Math.pow(input, 2));
        System.out.println("Function value logarithmic:");
        new DiapasonClass().diapason(1, 10,
                (input) -> Math.log(input));
    }
}
