package pos_system;

import java.io.*;
import java.util.*;

public class SaleReport {

    private List<Product> productList;
    private List<Sale> SalesList;
    private Scanner input = new Scanner(System.in);

    public SaleReport(List<Product> productList, List<Sale> SalesList) {
        this.productList = productList;
        this.SalesList = SalesList;
    }

    public boolean generateSaleReport() {
        
        System.out.println("Since their arent enough sales to generate we will generate a fake report");
        
        System.out.println("Please enter how many fake sales to generate ");
        int numSales;
        try {
            numSales = input.nextInt();
            input.nextLine();
            if (numSales < 1) {
                System.out.println("Please enter correct number of fake sales");
                return false;
            }
            List<Sale> saleArray = generateFakeSales(numSales);
            Collections.sort(saleArray);
            System.out.println("For total revenue report choose(1)");
            System.out.println("For report by customer	(2)");
            System.out.println("To go back(0)");
            int choice = input.nextInt();
            input.nextLine();
            if (choice < 0 || choice > 2) {
                throw new InputMismatchException();
            }
            String report = "";
            switch (choice) {
                case 0:
                    return true;
                case 1:
                    report = totalRevenueInfomation(saleArray);
                    break;
                case 2:
                    report = reportByCustomer(saleArray);
                    break;
            }
            System.out.println(report);
            System.out.println("- To show the sales data that this report is based on(1)");
            System.out.println("- To save report to file	(2)");
            System.out.println("- To go back(0)");
            choice = input.nextInt();
            input.nextLine();
            if (choice < 0 || choice > 2) {
                throw new InputMismatchException();
            }
            switch (choice) {
                case 0:
                    return true;
                case 1:
                    for (Sale s : saleArray) {
                        System.out.println(s);
                    }
                    System.out.println(" To save report to file	(1)");
                    System.out.println(" To go back(0)");
                    choice = input.nextInt();
                    input.nextLine();
                    if (choice == 1) {
                        saveReportToFile(report);
                        return true;
                    } else if (choice == 0) {
                        return true;
                    }
                case 2:
                    saveReportToFile(report);
                    return true;
            }
        } catch (InputMismatchException ex) {
            System.out.println("Enter a valid input ");
            return false;
        }
        return true;
    }

    private List<Sale> generateFakeSales(int n) {
        
        String[] fakeNames = generateFakeNames(n);
        cart[] fakeCarts = generateFakeCarts(n);
        double[] fakeDiscounts = generateFakeDiscounts(n);

        for (int i = 0; i < n; i++) {
            Sale s = new Sale(fakeNames[i], fakeCarts[i], fakeDiscounts[i]);
            this.SalesList.add(s);
        }

        return SalesList;
    }

    private cart[] generateFakeCarts(int n) {
       cart[] fakeCarts = new cart[n];
        for (int i = 0; i < fakeCarts.length; i++) {
            Cart cart = new Cart();
           
            int numberOfCartItems = 1 + (int) (Math.random() * 10);
            for (int j = 0; j < numberOfCartItems; j++) {
                
                int index = (int) (Math.random() * this.productList.size());
                Product p = this.productList.get(index);
                
                while (cart.containsItem(p)) {
                    index = (int) (Math.random() * this.productList.size());
                    p = this.productList.get(index);
                }
                
                int quantity = 1 + (int) (Math.random() * p.getInventoryLevel());


                cart.addItem(p, quantity);
            }
            fakeCarts[i] = cart;
        }
        return fakeCarts;
    }

    private String[] generateFakeNames(int n) {
        String firstName = "Mohammad Abdullah Naif Saud Khalid Abdulrahman Badr Nasser Sultan"
                + " Abeer Lama Noura Sara Nouf Hessah Reham Amjad Reema Reem Raghad";
        String[] firstNameArray = firstName.split(" ");

        String lastName = "AlKhalid AlAbdullah AlSultan AlAbdulaziz AlBadr AlNasser"
                + " AlSaif AlQahtani AlOtaibi Alshammary Alharbi";
        String[] lastNameArray = lastName.split(" ");

        String[] fullNameArray = new String[n];
        for (int i = 0; i < n; i++) {
           
            int fNameIndex = (int) (Math.random() * firstNameArray.length);

            
            int lNameIndex = (int) (Math.random() * lastNameArray.length);

            
            String fullName = firstNameArray[fNameIndex] + " " + lastNameArray[lNameIndex];
            fullNameArray[i] = fullName;
        }
        return fullNameArray;
    }

    private double[] generateFakeDiscounts(int n) {
        double[] discountArray = new double[n];
        for (int i = 0; i < n; i++) {
            
            int randDiscount = (int) (Math.random() * 26);
            discountArray[i] = randDiscount;
        }
        return discountArray;
    }

    private String totalRevenueInfomation(List<Sale> saleArray) {
        String report = "";
        double totalRevenue = 0;
        double averageSaleAmount = 0;
        int totalItemsSold = 0;
        int totalQuantitySold = 0;

        for (Sale s : saleArray) {
            
            totalRevenue += s.getSalePrice();
            
            totalItemsSold += s.getcart().getSize();
            
            for (int i = 0; i < s.getcart().getSize(); i++) {
                totalQuantitySold += s.getcart().getQuantities()[i];
            }
        }
        averageSaleAmount = totalRevenue / Sale.getNumSales();
       
        HashMap<String, Integer> productMap = new HashMap<>();
        for (Sale s : saleArray) {
            for (int i = 0; i < s.getcart().getSize(); i++) {
                String name = s.getcart().getItems()[i].getName();
                int newQuantity = s.getcart().getQuantities()[i];

                if (productMap.containsKey(name)) {
                    int oldQuantity = productMap.get(name);
                    productMap.put(name, oldQuantity + newQuantity);
                } else {
                    productMap.put(name, newQuantity);
                }
            }
        }
        
        String highestProductName = "";
        int highestProductQuantity = 0;
        for (String key : productMap.keySet()) {
            int value = productMap.get(key);

            if (value > highestProductQuantity) {
                highestProductName = key;
                highestProductQuantity = value;
            }
        }


        report = getTotalRevenueReport(totalRevenue, averageSaleAmount,
                totalItemsSold, totalQuantitySold,
                highestProductName, highestProductQuantity);

        return report;
    }

    private String reportByCustomer(List<Sale> saleArray) {

       
        TreeMap<String, Double> CustomerRevenueMap = new TreeMap<>();
        for (Sale s : saleArray) {
            String customerName = s.getCustomerName();
            if (CustomerRevenueMap.containsKey(customerName)) {
                double oldRevenue = CustomerRevenueMap.get(customerName);
                double newRevenue = s.getSalePrice();
                CustomerRevenueMap.put(customerName, oldRevenue + newRevenue);
            } else {
                CustomerRevenueMap.put(customerName, s.getSalePrice());
            }
        }
        Map<String, Double> sortedCustomerMap = sortByValue(CustomerRevenueMap);
        Map<String, Double> top5Customers = getTop5Customers(sortedCustomerMap);

        String report = "";
        for (String customerName : top5Customers.keySet()) {
            report += "\n-----------------------------\n";
            report += String.format("customer Name: %s\n", customerName);
            report += String.format("Total Revenue: %.2f SAR\n", top5Customers.get(customerName));
        }
        return report;
    }

    private void saveReportToFile(String report) {
        System.out.println("Enter file name to save report to: (example.txt)");
        String fileName = input.nextLine().trim();
        File outFile = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(outFile);
            pw.print(report);
            pw.close();
            System.out.println("Report is saved to " + fileName + " successfully.");
        } catch (FileNotFoundException ex) {
            System.out.println("File name is not correct, please enter it as FILENAME.txt");;
        }

    }

    private String getTotalRevenueReport(double totalRevenue, double averageSaleAmount,
            int totalItemsSold, int totalQuantitySold,
            String highestProductName, int highestProductQuantity) {

        String report = "[TotalRevenueReport]";
        report += String.format("Total revenue generated: ", totalRevenue);
        report += String.format("Total number of sales: ", Sale.getNumSales());
        report += String.format("Average sale Amount: ", averageSaleAmount);
        report += String.format("Total number of products listed: ", Product.getNumProducts());
        report += String.format("Total Items Sold: ", totalItemsSold);
        report += String.format("Total Quantity Sold: ", totalQuantitySold);
        report += String.format("Most popular item is which sold ", highestProductName, highestProductQuantity);
        report += "------------------------------------------------------";
        return report;
    }

    private Map<String, Double> sortByValue(TreeMap<String, Double> CustomerRevenueMap) {
        
        List<Map.Entry<String, Double>> list = new ArrayList<>(CustomerRevenueMap.entrySet());

        Collections.sort(list, new CustomerReveComp());

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private Map<String, Double> getTop5Customers(Map<String, Double> sortedCustomerMap) {
        String report = "";
        Map<String, Double> top5Customers = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, Double> entry : sortedCustomerMap.entrySet()) {
            if (count == 5) {
                break;
            }
            top5Customers.put(entry.getKey(), entry.getValue());
            count++;
        }
        return top5Customers;

    }

}
