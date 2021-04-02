package com.example.demo.model;

import java.util.List;

public class JSONModel {
    List<WinnerModel> Min;

    List<WinnerModel> Max;

    public List<WinnerModel> getMin() {
        return Min;
    }

    public void setMin(List<WinnerModel> min) {
        Min = min;
    }

    public List<WinnerModel> getMax() {
        return Max;
    }

    public void setMax(List<WinnerModel> max) {
        Max = max;
    }
}
