package mg.ratombotsoa.transactionstats.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	
	@Test
	public void testPostTransaction_whenDateIsInvalid() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("amount", new BigDecimal(100));
		data.put("timestamp", new Date(System.currentTimeMillis() + 9660000).getTime());
		
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(new JSONObject(data).toString()))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("should never be in the future")));
	}
	
	@Test
	public void testPostTransaction_whenDateBeforeBoundaryDate() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("amount", new BigDecimal(100));
		data.put("timestamp", new Date(System.currentTimeMillis() - 9660000).getTime());
		
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(new JSONObject(data).toString()))
			.andExpect(status().is2xxSuccessful())
			.andExpect(status().isNoContent());
	}
	
	@Test
	public void testSaveTransaction_whenDateIsInComputedRange() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("amount", new BigDecimal(100));
		data.put("timestamp", new Date(System.currentTimeMillis() - 50000).getTime());
		
		mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(new JSONObject(data).toString()))
			.andExpect(status().is2xxSuccessful())
			.andExpect(status().isCreated());
	}
}
