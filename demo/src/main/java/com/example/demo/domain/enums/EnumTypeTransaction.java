package com.example.demo.domain.enums;

public enum EnumTypeTransaction {

    CREDITO("CREDITO"),
    DEBITO("DEBITO");

    private String type;

    EnumTypeTransaction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
