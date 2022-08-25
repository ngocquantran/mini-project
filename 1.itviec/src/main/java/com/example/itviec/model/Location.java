package com.example.itviec.model;

public enum Location {
    hanoi("Hà Nội"),hochiminh("Hồ Chí Minh"),danang("Đà Nẵng"),other("Khác");

    public String address;

    Location(String value) {
        this.address = value;
    }

    @Override
    public String toString() {
        return address;
    }
}
