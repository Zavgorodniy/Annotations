import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by ADMIN on 28.06.2015.
 */

public class Test {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        MyClass myClass = new MyClass();

        toPrint(myClass);
        analyzeAndSet(myClass);
        toPrint(myClass);

    }

    public static void toPrint(Object obj) throws IllegalAccessException {

        Field[] fields = obj.getClass().getFields();
        HashMap myArr = new HashMap();

        for (Field field : fields) {
            myArr.put(field.getName(), field.get(obj));
        }

        System.out.println(myArr.toString());

    }

    public static void analyzeAndSet(Object obj) throws IllegalAccessException, InvocationTargetException {

        Class c = obj.getClass();

        Field[] fields  = c.getFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Person.class)) {
                Person person = field.getAnnotation(Person.class);
                field.set(obj, person.name());
            }

            if (field.isAnnotationPresent(Age.class)) {
                Age age = field.getAnnotation(Age.class);
                field.set(obj, age.years());
            }

            if (field.isAnnotationPresent(Weight.class)) {
                Weight weight = field.getAnnotation(Weight.class);
                field.set(obj, weight.kg() * weight.kg());
            }

        }

        Method[] methods = c.getMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Hello.class)) {

                Hello hello = method.getAnnotation(Hello.class);
                String[] args = {hello.a(), hello.b()};

                System.out.println();
                System.out.println("Try method " + method.getName() + "() with annotation args:");
                method.invoke(obj, args);
                System.out.println();
            }

        }

    }
}
