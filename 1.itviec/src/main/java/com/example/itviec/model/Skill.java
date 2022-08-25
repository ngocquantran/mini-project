package com.example.itviec.model;

public enum Skill {
    php("PHP"),python("Python"),java("Java"),spring("Spring"),sql("SQL");
    public String skillType;

    Skill(String skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return skillType ;
    }
}
