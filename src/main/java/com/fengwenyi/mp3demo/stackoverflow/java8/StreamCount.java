package com.fengwenyi.mp3demo.stackoverflow.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.fengwenyi.mp3demo.stackoverflow.java8.StreamCount.WeatherStationQ2.stations;

public class StreamCount {
    public static void main(String[] args) {
        //Creates a series of MeasurementQ1 object, creates a list and populate the list
        MeasurementQ2 m = new MeasurementQ2(1, 2.0);
        MeasurementQ2 n = new MeasurementQ2(13, 8.1);
        MeasurementQ2 o = new MeasurementQ2(25, 12.5);
        List<MeasurementQ2> mesearements = new ArrayList<>();
        mesearements.add(m);
        mesearements.add(n);
        mesearements.add(o);
        MeasurementQ2 p = new MeasurementQ2(3, 23.6);
        MeasurementQ2 q = new MeasurementQ2(11, 13.8);
        MeasurementQ2 r = new MeasurementQ2(28, 14.5);
        List<MeasurementQ2> measure = new ArrayList<>();
        measure.add(p);
        measure.add(q);
        measure.add(r);
        //Creates the WeatherStationQ1 object
        WeatherStationQ2 WS = new WeatherStationQ2("Galway", mesearements);
        WeatherStationQ2 WS2 = new WeatherStationQ2("Dublin", measure);
        stations.add(WS);
        stations.add(WS2);
        countTemperature(19.0,10.8,3);
    }

    public static void countTemperature(double t1, double t2, int r) {
        stations.stream()
                .map(station ->
                         station.getMeasurements()
                                .stream()
                                .filter(e -> Math.abs(e.getTime() - t1) <= r)
                                .count()
                )
                .forEach(System.out::println);
    }

    @Data
    @AllArgsConstructor
    public static class MeasurementQ2{
        private int time;
        private double temperature;
    }

    @Data
    @AllArgsConstructor
    public static class WeatherStationQ2{
        //Setting up class attributes
        private String city;
        private List<MeasurementQ2> measurements;
        public static List<WeatherStationQ2> stations= new ArrayList<>();
    }
}
