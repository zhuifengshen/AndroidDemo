package zhangchuzhao.site.intent;

import java.io.Serializable;

/**
 * Created by Devin on 2016/11/18.
 * Person person = (Person)getIntent().getSerializableExtra("person_data");
 */

public class Person implements Serializable {
    private String name;
    private int age;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
