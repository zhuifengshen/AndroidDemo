package zhangchuzhao.site.test;

/**
 * Created by Devin on 2016/11/18.
 */

public class TestMain {

    public static final String tag = new Object(){
        public String getClassName(){
            return this.getClass().getSimpleName();
        }
    }.getClassName();

    String tag2 = new Throwable().getStackTrace()[1].getClass().getSimpleName();

    String tag3 = Thread.currentThread().getStackTrace()[2].getClass().getSimpleName();

    public static void main(String[] args) {
        System.out.println(tag);

        String className0 = new SecurityManager(){
            public String getClassName(){
                return getClassContext()[1].getName();
            }
        }.getClassName();
        String className1 = new Throwable().getStackTrace()[0].getClass().getSimpleName();
        String className2 = Thread.currentThread().getStackTrace()[1].getClass().getSimpleName();
        String className3 = new Object(){
            public String getClassName(){
                String className = this.getClass().getName();
                return className.substring(0, className.lastIndexOf('$'));
            }
        }.getClassName();
        System.out.println(className0);
        System.out.println(className1);
        System.out.println(className2);
        System.out.println(className3);

    }
}
