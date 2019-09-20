/*
 * package com.bankapplication.controller;
 * 
 * import static org.junit.Assert.assertNotNull; import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.mockito.Mock; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
 * org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.MvcResult; import
 * org.springframework.test.web.servlet.RequestBuilder; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 * 
 * import com.bankapplication.repositories.CustomerRepository; import
 * com.bankapplication.service.CustomerServiceImpl;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @WebMvcTest(CustomerController.class) //@ContextConfiguration(classes =
 * {BankingApplication.class})
 * 
 * public class CustomerControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @MockBean private CustomerServiceImpl customerService;
 * 
 * @Mock private CustomerRepository customerRepository;
 * 
 * @Test public void getDiscountbyIdTest() throws Exception { //
 * when(customerService.getCustomerDetailsbyId()).thenReturn(customer);
 * RequestBuilder request =
 * MockMvcRequestBuilders.get("/customers",1).accept(MediaType.APPLICATION_JSON)
 * ;
 * 
 * MvcResult result =
 * mockMvc.perform(request).andExpect(status().isOk()).andReturn(); //
 * assertNotNull(result.getResponse()); }
 * 
 * @Test public void saveCustomerDetails() throws Exception { //
 * when(customerService.getCustomerDetailsbyId()).thenReturn(customer);
 * RequestBuilder request =
 * MockMvcRequestBuilders.post("/customers").accept(MediaType.APPLICATION_JSON);
 * MvcResult result =
 * mockMvc.perform(request).andExpect(status().isOk()).andReturn(); }
 * 
 * @Test public void deleteCustomerDetailsbyId() throws Exception {
 * RequestBuilder request =
 * MockMvcRequestBuilders.delete("/customers/{id}","1").accept(MediaType.
 * APPLICATION_JSON);
 * 
 * mockMvc.perform(request).andExpect(status().isOk()).andReturn(); }
 * 
 * }
 * 
 * 
 * 
 * //.andExpect(status().isOk()) // .andExpect(content().string("hello world"))
 * // .andReturn(); assertNotNull(result.getResponse());
 * //assertEquals("hello-world", result.getResponse().getContentAsString()); }
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @WebMvcTest(ShoppingServiceController.class)
 * 
 * @ContextConfiguration(classes = {AmazonShoppingApplication.class})
 * //@SpringBootApplication class ShoppingServiceImplTest2 {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @MockBean private CustomerRepository customerRepository;
 * 
 * @MockBean private FestivalDiscountRepository festivalDiscountRepository;
 * 
 * @Test public void getDiscountbyIdTest() throws Exception {
 * MockHttpServletRequestBuilder request =
 * MockMvcRequestBuilders.get("/getDetails/{id}")
 * .accept(MediaType.APPLICATION_JSON); MvcResult result =
 * mockMvc.perform(request).andReturn(); assertNotNull(result.getResponse());
 * 
 * }
 */