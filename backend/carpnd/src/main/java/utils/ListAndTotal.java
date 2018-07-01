package utils;

import java.util.List;

public class ListAndTotal<T> {

    public List<T> elementList;
    public int total;

    public ListAndTotal(List<T> elementList, int total){
        this.elementList = elementList;
        this.total = total;
    }

}
