package com.xubo.druid.scenes.entity;

/**
 * @Author xubo
 * @Date 2022/1/4 17:57
 * https://blog.csdn.net/csdn_wangchen/article/details/86623478  参考博客
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


}
