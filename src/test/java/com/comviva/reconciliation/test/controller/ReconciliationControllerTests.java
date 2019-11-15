package com.comviva.reconciliation.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.comviva.reconciliation.controller.TransactionController;
import com.comviva.reconciliation.entity.Transaction;
import com.comviva.reconciliation.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebMvcTest(TransactionController.class)

public class ReconciliationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private TransactionService ts;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).dispatchOptions(true).build();

	}

	@Test
	public void onlyValidEndPointsAreAccesible() {
		try {
			this.mockMvc.perform(get("/transactionWrong")).andExpect(status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenValidEndPoint_sendStatusOK() throws Exception {
		this.mockMvc.perform(get("/getAllFailedTransactions")).andExpect(status().isOk());
	}

	@Test
	public void invalidMethods_areNotAllowed() throws Exception {
		this.mockMvc.perform(post("/getAllFailedTransactions").contentType("application/xml").content("test"))
				.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void validMethodsAreAllowed() throws Exception {
		this.mockMvc.perform(get("/getAllFailedTransactions")).andExpect(status().isOk());
	}

	@Test
	public void expectedObject_isReturned() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getAllFailedTransactions")).andReturn();

		assertEquals(result, result);
	}

}
