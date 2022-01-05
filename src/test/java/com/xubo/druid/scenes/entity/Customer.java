package com.xubo.druid.scenes.entity;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Author xubo
 * @Date 2022/1/4 17:57
 * https://blog.csdn.net/csdn_wangchen/article/details/86623478  参考博客
 * 这个statement 方法里面功能太多了，如果我只用其中的计算积分的逻辑又得重新写一遍，一个方法建议只有一个逻辑，
 * 代码块越小，代码的功能越容易管理，代码处理和移动也就越方便
 * 重构还是多思考，这样进步才快
 */
public class Customer {

    private String name;
    private Vector<Rental> vectors = new Vector<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        vectors.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmout = 0;
        int integral = 0;
        String result = "current customer is " + name + " rental \n";
        Enumeration<Rental> rentals = vectors.elements();
        while (rentals.hasMoreElements()) {
            double thisAmout = 0;
            Rental each = rentals.nextElement();
            //计算金额
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmout += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmout += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmout += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmout += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmout += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }
            //计算积分
            integral++;
            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                    each.getDaysRented() > 1) {
                integral++;
            }

            totalAmout += thisAmout;
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmout + "\n";
        }
        result += " your cost is " + totalAmout + " increment integral ：" + integral;
        return result;
    }

    /**
     * 重构上面的statement方法
     * 将上面的statement 方法重构成五个方法 计算单个积分 单个价格 计算总积分 总价格 以及 一个简化版的statement对象
     *
     * @return
     */
    public String statement1() {
        double totalAmout = 0;
        int integral = 0;
        String result = "current customer is" + name + "rental \n";
        Enumeration<Rental> rentals = vectors.elements();
        while (rentals.hasMoreElements()) {
            Rental rental = rentals.nextElement();
            // 计算积分
            integral += rental.getIntegral();
            // 计算金额
            totalAmout += rental.getCharge();
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
        }
        result += " your cost is " + totalAmout + " increment integral ：" + integral;
        return result;
    }

    public double getTotalAmout() {
        double totalAmount = 0;
        Enumeration<Rental> rentals = vectors.elements();
        while (rentals.hasMoreElements()) {
            Rental rental = rentals.nextElement();
            totalAmount += rental.getCharge();
        }
        return totalAmount;
    }

    public int getIntegral() {
        int totalIntegral = 0;
        Enumeration<Rental> rentals = vectors.elements();
        while (rentals.hasMoreElements()) {
            Rental rental = rentals.nextElement();
            // 增加积分
            totalIntegral += rental.getIntegral();
        }
        return totalIntegral;
    }

}
