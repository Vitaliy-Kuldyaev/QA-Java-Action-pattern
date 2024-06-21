package annotations;

public class Injector {
    public static void inject(Object inst) {
        Class<?>[] interfaces = inst.getClass().getInterfaces();


       // Field[] fields = inst.getClass().getInterfaces().getDeclaredFields();

        int sdf=0;
    }
}
