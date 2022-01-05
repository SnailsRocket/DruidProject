package com.xubo.druid.scenes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xubo
 * @Date 2022/1/4 17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental {

    //表示某客户租了一部影片
    private Movie movie;
    private int daysRented;

    public double getCharge() {
        double amount = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (getDaysRented() > 2) {
                    amount += (getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                amount += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (getDaysRented() > 3) {
                    amount += (getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return amount;
    }

    public int getIntegral() {
        int integral = 0;
        integral++;
        if(getMovie().getPriceCode() == Movie.NEW_RELEASE && getDaysRented() > 1) {
            integral++;
        }
        return integral;
    }

}
