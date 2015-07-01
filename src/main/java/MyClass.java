/**
 * Created by ADMIN on 28.06.2015.
 */
public class MyClass {

    @Person(name = "Nick")
    public String name = "NoName";

    @Age(years = 28)
    public int years = 0;

    @Weight(kg = 10)
    public double kg = 0.0;

    @Hello(a = "Hello, ", b = "world!")
    public void toPrint(String a, String b) {
        System.out.println(a + b);
    }

}
