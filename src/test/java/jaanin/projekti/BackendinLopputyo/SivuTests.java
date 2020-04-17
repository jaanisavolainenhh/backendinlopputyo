package jaanin.projekti.BackendinLopputyo;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringRunner.class)
@WebMvcTest
class SivuTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test //juu delliä vaan tälle vittu.
	public void hellotesti() throws Exception
	{
        mockMvc.perform(get("")).andExpect(status().isOk()).andExpect(content().string(containsString("Lainatarjous")));
	}	


}
