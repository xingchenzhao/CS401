/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealerapp;

/**
 *
 * @author zhaoxingchen
 */
public class Cars {

    private String type;
    private String color;
    private double carPrice;
    private double optionalPrice;
    private double insurancePrice;
    private double totalPrice;

    public Cars() {
        type = "Corolla";
        color = "blue";
    }

    public Cars(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public void setOnlyCarPrice(String type) {
        switch (type) {
            case "Corolla":
                this.carPrice = 18500;
                break;
            case "Yaris":
                this.carPrice = 20000;
                break;
            case "RAV4":
                this.carPrice = 25000;
                break;
            default:
                break;
        }
    }

    public double getOnlyCarPrice() {
        return this.carPrice;
    }
   
    public void setInsurancePrice(String type) {
        insurancePrice = 0;
        switch (type) {
            case "3 years":
                this.insurancePrice = 3000;
                break;
            case "1 year":
                this.insurancePrice = 1000;
                break;
            case "none":
                this.insurancePrice = 0;
                break;
            default:
                break;

        }
    }

    public double getInsurancePrice() {
        return this.insurancePrice;
    }

    StringBuilder myOption = new StringBuilder();

    public void setOptionalPrice(boolean[] optionals) {
        myOption = new StringBuilder();
        optionalPrice = 0;
        if (optionals[0] || optionals[1] || optionals[2]) {
            myOption.append("\nChosen Optionals: ");
            if (optionals[0]) {
                myOption.append(" Power Locks ");
                optionalPrice += 300;
            } 
            if (optionals[1]) {
                myOption.append(" Power Windows ");
                optionalPrice += 500;
            } 
            if (optionals[2]) {
                myOption.append("Air-Conditioner");
                optionalPrice += 1000;
            }
            
            
        }
    }

    public double getOptionalPrice() {
        return optionalPrice;
    }

    public String getOptionalName() {
        return myOption.toString();
    }


//    public void setTotalPrice(){
//        totalPrice = getOnlyCarPrice() + getInsurancePrice() + getOptionalPrice();
//    }
    
    public String getTotalPrice() {
        setOnlyCarPrice(getType());
        totalPrice = getOnlyCarPrice() + getInsurancePrice() + getOptionalPrice();
        return String.format("%.2f", totalPrice);
    }

    public String getDescriptions(String carType, String carColor) {
        StringBuilder description = new StringBuilder();
        switch (carType) {
            case "Corolla":
                description.append("Toyota Corolla debuts at the 2016 New York Auto show celebrating its golden "
                        + "anniversary with a new 50th anniversary trim package");
                description.append("\nColor: ").append(carColor);
                description.append("\nStandard Feautures: Air-Condition");
                description.append(getOptionalName());
                break;
            case "Yaris":
                description.append("Toyota Yaris: the new compact Toyota vehicle will be available in the mid 2016");
                description.append("\nColor: ").append(carColor);
                description.append("\nStandard Feautures: None");
                description.append(getOptionalName());
                break;
            case "RAV4":
                description.append("Toyota RAV4: Toyota has upped the RAV4's safety features game for 2017 "
                        + "by adding pre-conllision braking, lane departure warning with steering assist");
                description.append("\nColor: ").append(carColor);
                description.append("\nStandard Feautures: Power Window");
                description.append(getOptionalName());
                break;
            default:
                break;

        }

        return description.toString();
    }
}
