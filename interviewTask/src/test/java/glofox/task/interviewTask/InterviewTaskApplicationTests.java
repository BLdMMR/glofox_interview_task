package glofox.task.interviewTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import glofox.task.interviewTask.controllers.bookings.BookingInputModel;
import glofox.task.interviewTask.controllers.classes.ClassInputModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InterviewTaskApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mock;

	@BeforeEach
	void configureMockMvc() {
		mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	public void getAllClasses_empty() throws Exception {
		mock.perform(get("/classes"))
				.andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	@Order(2)
	public void createClass_ValidArguments() throws Exception {
		ClassInputModel input = new ClassInputModel("pilates", new Timestamp(133456789), new Timestamp(232714565), 10);
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(input);

		System.out.println(json);

		mock.perform(post("/classes").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string("Success"));
	}

	@Test
	@Order(3)
	public void createClass_InvalidArguments_StartDateBiggerThanEndDate() throws Exception {
		ClassInputModel input = new ClassInputModel("pilates", new Timestamp(9999999), new Timestamp(1111111), 10);
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(input);

		mock.perform(post("/classes").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid input arguments"));

	}


	@Test
	@Order(4)
	public void createClass_InvalidArguments_CapacityEqualsZero() throws Exception {
		ClassInputModel input = new ClassInputModel("pilates", new Timestamp(1111111), new Timestamp(9999999), 0);
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(input);

		mock.perform(post("/classes").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid input arguments"));

	}

	@Test
	@Order(5)
	public void createClass_InvalidArguments_ClassNameIsEmpty() throws Exception {
		ClassInputModel input = new ClassInputModel("", new Timestamp(1111111), new Timestamp(9999999), 10);
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(input);

		mock.perform(post("/classes").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid input arguments"));

	}

	@Test
	@Order(6)
	public void getAllClasses_OneInstance() throws Exception {
		mock.perform(get("/classes"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@Order(13)
	public void getAllBookings_ExistingClass() throws Exception {
		mock.perform(get("/classes/pilates/bookings"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@Order(7)
	public void getAllBookings_NonExistingClass() throws Exception {
		mock.perform(get("/classes/pilates/bookings"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	@Order(8)
	public void createBooking_ValidArguments() throws Exception {
		String json = "{\"memberName\":\"John\",\"bookingTime\":\"1970-01-03T00:00:00\"}";

		mock.perform(post("/classes/pilates/bookings").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().string("Class booked successfully"));

	}

	@Test
	@Order(9)
	public void createBooking_InvalidArguments_NoName() throws Exception {
		BookingInputModel inputModel = new BookingInputModel("", new Timestamp(133456789));
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(inputModel);

		mock.perform(post("/classes/pilates/bookings").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid input arguments"));
	}

	@Test
	@Order(10)
	public void createBooking_ValidArguments_NoDate() throws Exception {
		BookingInputModel inputModel = new BookingInputModel("John", null);
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(inputModel);

		mock.perform(post("/classes/pilates/bookings").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Invalid input arguments"));
	}

	@Test
	@Order(11)
	public void createBooking_ValidArguments_WrongDate() throws Exception {
		String json2 = "{\"memberName\":\"John\",\"bookingTime\":\"1950-01-03T00:00:00\"}";

		mock.perform(post("/classes/pilates/bookings").contentType(MediaType.APPLICATION_JSON).content(json2))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("No class with the name pilates found at that time"));
	}

	@Test
	@Order(12)
	public void createBooking_ValidArguments_NoRequestBody() throws Exception {
		mock.perform(post("/classes/pilates/bookings").contentType(MediaType.APPLICATION_JSON).content(new byte[0]))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("No information to book class found"));
	}

	@Test
	@Order(14)
	public void createBooking_ValidArguments_NonExistingClass() throws Exception {
		BookingInputModel inputModel = new BookingInputModel("John", new Timestamp(1234567));
		ObjectMapper objMapper = new ObjectMapper();

		String json = objMapper.writeValueAsString(inputModel);

		mock.perform(post("/classes/yoga/bookings").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("No class with the name yoga found"));
	}



}
