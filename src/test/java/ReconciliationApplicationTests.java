
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.comviva.reconciliation.test.RequestServiceUnitTesting;
import com.comviva.reconciliation.test.TransactionDaoTest;
import com.comviva.reconciliation.test.TransactonServiceUnitTest;
import com.comviva.reconciliation.test.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ RequestServiceUnitTesting.class, TransactionDaoTest.class, TransactonServiceUnitTest.class,
		UserServiceTest.class })
public class ReconciliationApplicationTests {

}
