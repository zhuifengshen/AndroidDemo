package zhangchuzhao.site.intent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Devin on 2016/11/18.
 * Student student = (Student)getIntent().getParcelableExtra("student_data");
 */

public class Student implements Parcelable {

    private String name;
    private int age;


    protected Student(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public final static Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    //public final Creator<Student> CREATOR = new MyCreator<Student>();

    //Type parameter cannot be instantiated directly
    //class MyCreator<Student> implements Creator<Student>{
    //
    //    @Override
    //    public Student createFromParcel(Parcel source) {
    //        return new Student(source);
    //    }
    //
    //    @Override
    //    public Student[] newArray(int size) {
    //        return new Student[size];
    //    }
    //}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
