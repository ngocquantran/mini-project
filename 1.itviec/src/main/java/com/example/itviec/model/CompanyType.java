package com.example.itviec.model;

public enum CompanyType {
    product("Product"),outsource("Outsourcing");
    public String type;

    CompanyType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  type ;
    }
}
