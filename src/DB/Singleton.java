package DB;

public class Singleton {
    private static Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if (singleton == null){
            singleton=new Singleton();
        }else{
            return singleton;
        }
        return singleton;
    }
}
