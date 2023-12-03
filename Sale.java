package Project;


public class Sale implements Comparable<Sale> {

    private String customerName;
    private Cart cart;
    private double salePrice;
    private double discountRate;
    private static int numSales;

    public Sale(String customerName, Cart cart, double discountRate) {
        this.customerName = customerName;
        this.cart = cart;
        setDiscountRate(discountRate);
        this.salePrice = calculateSale();
        numSales++;
    }

    public double calculateSale() {
        double totalPrice = 0;

        for (int i = 0; i < this.cart.getItems().length; i++) {
            if (cart.getItems()[i] == null) {
                continue;
            }
            double price = cart.getItems()[i].calculateDiscount(discountRate);
            int quantity = cart.getQuantities()[i];
            totalPrice += price * quantity;
        }
        return totalPrice;

    }

    @Override
    public int compareTo(Sale o) {
        if (o == null) {
            return 1;
        }
        return Double.compare(salePrice, o.salePrice);
    }

    public static int getNumSales() {
        return numSales;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        discountRate = discountRate / 100;
        if (discountRate < 0 || discountRate > 1) {
            throw new IllegalArgumentException("Discount Rate cannot be negative or more than 100%!");
        }
        this.discountRate = discountRate;
    }


    @Override
    public String toString() {
        String result = "============[Sale Transaction]============\n";
        result += "Customer Name: " + this.customerName;
        result += "\n-----------------------------------\n ";
        result += this.cart;
        result += "\n-----------------------------------";
        result += String.format("\nTotal Sale Price: %.2f SAR", this.salePrice);
        result += String.format("\nDiscount rate: %.0f%%", (this.discountRate * 100));
        result += "\n==========================================";

        return result;
    }

}
