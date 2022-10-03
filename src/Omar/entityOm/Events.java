package Omar.entityOm;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
/**
 *
 * @author Drissi Omar
 */
public class Events {
    private String idevent;
    private String name;
    private String period;
    private String location;
    private Date  date ;
    private String available;
    private int price;

    public Events(String idevent, String name,String period,String location, Date date, String available,int price) {
        this.idevent = idevent;
        this.name = name;
        this.period=period;
        this.location=location;
        this.date =date;
        this.available = available;
        this.price=price;
    }




    public String getIdevent() {
        return idevent;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPeriod() {
        return period;
    }
    public String getLocation(){
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Omar.entityOm.Events{" +
                "idevent='" + idevent + '\'' +
                ", name='" + name + '\'' +
                ", period='" + period + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", available='" + available + '\'' +
                ", price=" + price +
                '}';
    }
}
