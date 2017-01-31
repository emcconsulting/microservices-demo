package io.pivotal.microservices.accounts;

import static org.junit.Assert.assertEquals;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.pivotal.microservices.services.accounts.AccountsServer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class AccountServiceUnitTest {
	
	@Configuration
	static class AccountServiceTestContextConfiguration {
		
		@Bean
		public AccountsServer accountService() {
			return new AccountsServer();
		}
		
		@Bean
		public AccountRepository accountRepository() {
			return Mockito.mock(AccountRepository.class);
		}
	}
	
	@Autowired
	private AccountsServer accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Before
	public void setup() {
		
		Account account = new Account("12", "Vaibhav");
		
		Mockito.when(accountRepository.findByNumber("12")).thenReturn(account);
		
	}
	
	@Test()
	public void testGetAccountDetail() {
		//fail("Not yet implemented");
		Account account = accountService.getAccountDetail("12");
		assertEquals("12",account.getNumber());
		System.out.println(account.getOwner());
	}

}
