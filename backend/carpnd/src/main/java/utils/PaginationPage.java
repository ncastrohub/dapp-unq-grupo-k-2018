package utils;

import java.util.List;

public class PaginationPage<T> {
    public List<T> elementList;
    public Integer total;
    public String beforeUrl;
    public String nextUrl;
}
