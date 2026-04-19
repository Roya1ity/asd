package com.example.hello;

import com.example.hello.controler.HelloControler;
import com.example.hello.dto.RequestLogin;
import com.example.hello.dto.ResponseUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HelloApplicationTests {

	@Autowired
	private HelloControler controler;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		assertThat(controler).isNotNull();
	}

	@Test
	void testLogin() {
		String result = controler.login("skawsns521","1234");
		assertThat(result).isEqualTo("로그인 실패");
	}

	@Test
	void testLoginMM() throws Exception {
		mockMvc.perform(get("/login")
				.param("id","skawns521")
				.param("pw","1234"))
				.andExpect(status().isOk())
				.andExpect(content().string("로그인 성공"));
	}

	@Test
	void testSigninMM() throws Exception {
		RequestLogin body = new RequestLogin();
		body.setId("skawns521");
		body.setPw("1234");

		mockMvc.perform(post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andExpect(content().string("로그인 성공"));
	}

	@Test
	void testGetUser() throws Exception {
		ResponseUser body = new ResponseUser("1","페이커","faker_T1@naver.com");

		mockMvc.perform(get("/user/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(body)));
	}
}
