package hoja2.entry807;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IntegerList implements Serializable{
    private static IntegerList instance;
    private final List<Integer> intList;

    private IntegerList() {
        intList = new ArrayList<>();
    }

    public static IntegerList getInstance() {
        if (instance == null) {
            instance = new IntegerList();
        }

        return instance;
    }

    public void insertInt(final int i) {
        intList.add(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : intList) {
            sb.append("\n-> " + String.valueOf(i));
        }

        return sb.toString();
    }
}
