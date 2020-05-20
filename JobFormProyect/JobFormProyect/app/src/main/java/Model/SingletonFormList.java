package Model;

import java.util.ArrayList;

public class SingletonFormList  {

    private static SingletonFormList mInstance;
    private static ArrayList<Form> list = null;

    public static SingletonFormList getInstance() {
        if(mInstance == null)
            mInstance = new SingletonFormList();

        return mInstance;
    }

    private SingletonFormList() {
        list = new ArrayList<>();
    }

    public ArrayList<Form> getArray() {
        return this.list;
    }

    public void addToArray(Form value) {

        list.add(value);
    }
    public void removeFromArray(Form value){
        list.remove(value);
    }
}
