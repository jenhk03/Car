package com.example.car.Model;

import java.util.List;

public class ResponseModel
{
    private String code, message;
    private List<CarModel> data;
    public String getCode()
    {
        return code;
    }
    public String getMessage()
    {
        return message;
    }
    public List<CarModel> getData()
    {
        return data;
    }
}