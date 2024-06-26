import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        System.out.println("Customer removed from bank registry.");
    }

    public Customer getCustomerByPin(int pin) {
        for (Customer customer : customers) {
            if (customer.getPin() == pin) {
                return customer;
            }
        }
        return null;
    }

    public String getAllCustomerInfo() {
        StringBuilder info = new StringBuilder();
        for (Customer customer : customers) {
            info.append(customer.toString()).append("\n");
        }
        return info.toString();
    }
}
