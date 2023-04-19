package com.example.car;

import java.util.ArrayList;

public class CarData
{
    public static String[][] data = new String[][]
            {
            };
    public static ArrayList<CarModel> takeCarData()
    {
        ArrayList<CarModel> carData = new ArrayList<>();
        for (String[] varData : data)
        {
            CarModel model = new CarModel();
            model.setName(varData[0]);
            model.setAbout(varData[1]);
            model.setPhoto(varData[2]);
            carData.add(model);
        }
        return carData;
    }
}