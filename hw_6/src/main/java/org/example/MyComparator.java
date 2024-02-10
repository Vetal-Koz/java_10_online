package org.example;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class MyComparator implements Comparator<Object> {

    Map<String, Long> map;

    public MyComparator(Map<String, Long> map) {
        this.map = map;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (map.get(o2) == map.get(o1))
            return 1;
        else
            return (map.get(o2)).compareTo(map.get(o1));

    }
}
