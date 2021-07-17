package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Customer service test.
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    /**
     * The Test me.
     */
    private CustomerService testMe;

    /**
     * The Customer repository.
     */
    @Mock
    private CustomerRepository customerRepository;

    /**
     * The Customer service.
     */
    @Mock
    private CustomerService mockMe;

    /**
     * The Captor customer.
     */
    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<String> captorString;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<Item> captorItem;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<CreditCard> captorCreditCard;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<OrderDetails> captorOrderDetails;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    /**
     * The Customer.
     */
    private Customer customer;

    /**
     * The Customer.
     */
    private Item item;

    /**
     * The Customer.
     */
    private CreditCard creditCard;

    /**
     * The Customer.
     */
    private OrderDetails orderDetails;

    /**
     * The Customer.
     */
    private UUID uuid;

    /**
     * The Customer.
     */
    private Cart cart;

    /**
     * The Customer.
     */
    private List<Item> items;


    CustomerServiceTest() {
    }

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        customer = new Customer();
        testMe = new CustomerService(customerRepository);

        orderDetails = new OrderDetails(
                "TEST",
                "TestMe@gmail.com",
                new ArrayList<>(),
                43.24
        );

        uuid = UUID.randomUUID();

        orderDetails.setOrderUUID(uuid);
        customer.setOrderDetailsList(new ArrayList<>());
        customer.getOrderDetailsList().add(orderDetails);

        item = new Item(
                "Test Item",
                "Test description",
                33.54
        );
        items = new ArrayList<>();
        items.add(item);

        cart = new Cart(
            items,
                "testme@gmail.com",
                93.22
        );
        customer.setCart(cart);

        creditCard = new CreditCard(
                "Test Name",
                "4656085451464353",
                "05/23",
                "231",
                "4353"
        );
        customer.setCreditCards(new ArrayList<>());
        customer.getCreditCards().add(creditCard);
    }

    /**
     * Exists by phone number.
     */
    @Test
    void existsByPhoneNumber() {
        given(customerRepository.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by phone number.
     */
    @Test
    void doesNotExistsByPhoneNumber() {
        given(customerRepository.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    /**
     * Add.
     *
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws CustomerAlreadyExistsException the customer already exists exception
     */
    @Test
    void add() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        testMe.add(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        testMe.delete(this.customer);

        then(customerRepository).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Update.
     */
    @Test
    void update() {
        testMe.update(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Find all customers.
     */
// Do later
    @Test
    @Disabled
    void findAllCustomers() {
    }

    /**
     * Find all credit cards.
     */
    @Test
    void findAllCreditCards() {
        List<CreditCard> list = customer.getCreditCards();
        List<CreditCard> test = testMe.findAllCreditCards(customer);

        assertThat(list).isEqualTo(test);
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
        String email = "testMe@gmail.com";
        given(customerRepository.existsByEmail(email)).willReturn(true);

        boolean bool = testMe.existsByEmail(email);

        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isTrue();
    }

    /**
     * Exists by email.
     */
    @Test
    void doesNotExistsByEmail() {
        String email = "testMe@gmail.com";
        given(customerRepository.existsByEmail(email)).willReturn(false);

        boolean bool = testMe.existsByEmail(email);

        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isFalse();
    }

    /**
     * Gets by email.
     */
    @Test
    void getByEmail() {
        String email = "testMe@gmail.com";
        testMe.getByEmail(email);

        then(customerRepository).should().getByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
    }

    /**
     * Add to cart.
     */
    @Test
    void addToCart() {
        mockMe.addToCart(customer, item);

        then(mockMe).should().addToCart(captorCustomer.capture(), captorItem.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Remove from cart.
     */
    @Test
    void removeFromCart() {

        int size = customer.getCart().getItems().size();

        testMe.removeFromCart(customer, "Test Item");

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue().getCart().getItems().size()).isEqualTo(size-1);
    }

    /**
     * Empty cart.
     */
    @Test
    void emptyCart() {

        testMe.emptyCart(customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(customer).isEqualTo(captorCustomer.getValue());
        assertThat(customer.getCart().getTotal()).isEqualTo(0.00);
        assertThat(customer.getCart().getItems()).isEmpty();
    }

    /**
     * Gets cart.
     */
    @Test
    void getCart() {

        Cart customerCart = customer.getCart();
        Cart testCart = testMe.getCart(customer);

        assertThat(customerCart).isEqualTo(testCart);
    }

    /**
     * Add credit card.
     */
    @Test
    void addCreditCard() {

        int size = customer.getCreditCards().size();
        CreditCard creditCard = new CreditCard(
                "Test Name",
                "4656085451466403",
                "05/23",
                "231",
                "6403"
        );

        testMe.addCreditCard(customer, creditCard);

        then(customerRepository).should().save(captorCustomer.capture());

        // Can assert that credit card list length was subtracted by 1
        assertThat(captorCustomer.getValue().getCreditCards().size()).isEqualTo(size+1);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Remove credit card.
     */
    @Test
    void removeCreditCard() {

        int size = customer.getCreditCards().size();
        testMe.removeCreditCard(customer, "4353");

        then(customerRepository).should().save(captorCustomer.capture());

        // Can assert that credit card list length was subtracted by 1
        assertThat(captorCustomer.getValue().getCreditCards().size()).isEqualTo(size-1);
        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Add order.
     */
    @Test
    void addOrder() {

        testMe.addOrder(customer, orderDetails);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCustomer.getValue().getOrderDetailsList().get(0)).isEqualTo(orderDetails);
    }

    /**
     * Update order.
     */
    @Test
    void updateOrder() {

        String orderStatus = "TEST1";

        testMe.updateOrder(customer, uuid, orderStatus);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(customer.getOrderDetailsList().get(0).getOrderStatus()).isEqualTo("TEST1");
        assertThat(customer).isEqualTo(captorCustomer.getValue());
    }

    /**
     * Find all orders.
     */
    @Test
    void findAllOrders() {

        List<OrderDetails> orderDetails = customer.getOrderDetailsList();
        List<OrderDetails> findAllOrders = testMe.findAllOrders(customer);

        assertThat(findAllOrders).isEqualTo(orderDetails);
    }
}