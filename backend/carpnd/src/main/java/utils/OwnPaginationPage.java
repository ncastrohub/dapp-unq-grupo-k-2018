package utils;

import org.codehaus.jackson.annotate.JsonIgnore;
import java.util.List;


public class OwnPaginationPage<T> {
    
    public List<T> elementList;

    public String beforeUrl;
    public String nextUrl;

    public OwnPaginationPage(){}

    @JsonIgnore
    public List<T> getElementList() {
        return elementList;
    }

    public void setElementList(List<T> elementList) {
        this.elementList = elementList;
    }

    public String getBeforeUrl() {
        return beforeUrl;
    }

    public void setBeforeUrl(String beforeUrl) {
        this.beforeUrl = beforeUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
