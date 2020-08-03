package com.xxx.recommendfilm.model.film;

import java.util.List;

public class ClassifyListModel {

    private List<String> classifyList;

    public ClassifyListModel(List<String> classifyList) {
        this.classifyList = classifyList;
    }

    public List<String> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<String> classifyList) {
        this.classifyList = classifyList;
    }
}
