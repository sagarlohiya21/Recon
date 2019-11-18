package com.comviva.reconciliation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RequestServiceUnitTesting.class, TransactionDaoTest.class, TransactonServiceUnitTest.class,
		UserServiceTest.class, UserDaoTest.class })
public class ReconciliationApplicationTests {

}
