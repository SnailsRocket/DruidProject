package com.xubo.druid.utils;

/**
 * @Author xubo
 * @Date 2021/12/31 11:29
 * 获取经纬度
 */
public class GetDistance {


    public double getDistance4J(double lon1, double lat1, double lon2, double lat2) {

        // 地球半径
        double EARTH_RADIUS = 6378.138;

        //经纬度转换成弧度
        lat1 = ConvertDegreesToRadians(lat1);
        lon1 = ConvertDegreesToRadians(lon1);
        lat2 = ConvertDegreesToRadians(lat2);
        lon2 = ConvertDegreesToRadians(lon2);

        //差值
        double vLon = Math.abs(lon1 - lon2);
        double vLat = Math.abs(lat1 - lat2);

        double h = HaverSine(vLat) + Math.cos(lat1) * Math.cos(lat2) * HaverSine(vLon);

        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));

        return distance;
    }

    // HaverSine公式
    public double HaverSine(double x)
    {
        double v = Math.sin(x / 2);
        return v * v;
    }

    // 将角度换算为弧度
    public static double ConvertDegreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }



}
