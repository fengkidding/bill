package com.bill.apattern.zuhe;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    List<Component> corp = new ArrayList<>(10);

    public Composite(String info) {
        super.info = info;
    }

    public void add(Component component) {
        corp.add(component);
    }

    public List<Component> getCorp() {
        return corp;
    }
}
