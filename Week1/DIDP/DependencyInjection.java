package DIDP;


interface CustomerRepository {
  String findCustomerById(int id);
}

// Concrete Repository Implementation
class CustomerRepositoryImpl implements CustomerRepository {
  @Override
  public String findCustomerById(int id) {
      // Simulate finding a customer in a database
      if (id == 1) {
          return "John Doe";
      } else {
          return "Customer not found.";
      }
  }
}

// Service Class
class CustomerService {
  private CustomerRepository customerRepository;

  // Constructor Injection
  public CustomerService(CustomerRepository customerRepository) {
      this.customerRepository = customerRepository;
  }

  public String getCustomerName(int id) {
      return customerRepository.findCustomerById(id);
  }
}

// Test Class
public class DependencyInjection {
  public static void main(String[] args) {
      // Create a concrete repository instance
      CustomerRepository customerRepository = new CustomerRepositoryImpl();

      // Inject the repository into the service using constructor injection
      CustomerService customerService = new CustomerService(customerRepository);

      // Use the service to find a customer
      String customerName = customerService.getCustomerName(1);
      System.out.println("Customer Name: " + customerName);

      // Attempt to find a customer that doesn't exist
      String unknownCustomer = customerService.getCustomerName(2);
      System.out.println("Customer Name: " + unknownCustomer);
  }
}
