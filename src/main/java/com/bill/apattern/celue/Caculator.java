package com.bill.apattern.celue;


public enum Caculator {

    ADD("+"){
        @Override
        public int exec(int a, int b) {
            return a+b;
        }
    },
    SUB("-"){
        @Override
        public int exec(int a, int b) {
            return a-b;
        }
    };

    private String value;

    private Caculator(String value){
        this.value=value;
    }

    public abstract int exec(int a,int b);
}
