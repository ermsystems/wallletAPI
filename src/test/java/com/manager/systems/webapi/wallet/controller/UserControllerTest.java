package com.manager.systems.webapi.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.systems.webapi.wallet.dto.UserDTO;
import com.manager.systems.webapi.wallet.entity.User;
import com.manager.systems.webapi.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	private static final String URL = "/user";

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mvc;

	@Test
	public void testSave() throws Exception {
		
		BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		this.mvc.perform(MockMvcRequestBuilders.post(URL).
				content(getJsonPayload()).
				contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).
				andExpect(status().isCreated());
	}

	public User getMockUser() {
		final User user = new User();
		user.setEmail("teste@manager.com");
		user.setName("teste");
		user.setPassword("123456");
		return user;
	}

	public String getJsonPayload() throws Exception {
		final UserDTO dto = new UserDTO();
		dto.setEmail("teste@manager.com");
		dto.setName("teste");
		dto.setPassword("123456");

		final ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
}