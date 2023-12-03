package pos_system;

import java.util.Objects;

public class Product implements Discountable, Comparable<Product> {

    private String name;
    private double price;
    private int inventoryLevel;
    
    private static int numProducts;

    public Product(String name, double price, int inventoryLevel) {
        this.name = name;
        this.price = price;
        this.inventoryLevel = inventoryLevel;
        numProducts++;
    }

    public static int getNumProducts() {
        return numProducts;
    }

    public static void decreaseNumProducts() {
        numProducts--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventoryLevel() {
        return inventoryLevel;
    }

    public void setInventoryLevel(int inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public double calculateDiscount(double discountRate) {
        return this.price - (this.price * discountRate);
    }

    @Override
    public int compareTo(Product o) {
        if (o == null) {
            return 1;
        }
        return Double.compare(this.price, o.price);
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + ", inventoryLevel=" + inventoryLevel + '}';
    }

}
