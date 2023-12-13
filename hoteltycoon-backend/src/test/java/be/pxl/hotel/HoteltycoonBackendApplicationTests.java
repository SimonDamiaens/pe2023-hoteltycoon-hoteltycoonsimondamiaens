package be.pxl.hotel;

import be.pxl.hotel.domain.UnsufficientMoneyException;
import be.pxl.hotel.domain.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HoteltycoonBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testWallet(){

		Wallet wallet = new Wallet(100);
		assertEquals(100, wallet.getAmount());


		wallet.registerPayment(50, "Payment for item");
		assertEquals(50, wallet.getAmount());
		assertEquals(2, wallet.getTransactions().size());

		// Test registerPayment with insufficient balance
		assertThrows(UnsufficientMoneyException.class, () -> {
			wallet.registerPayment(70, "Payment for item");
		});
		assertEquals(50, wallet.getAmount());
		assertEquals(2, wallet.getTransactions().size());

		// Test registerPayment with invalid amount
		assertThrows(IllegalArgumentException.class, () -> {
			wallet.registerPayment(0, "Invalid payment");
		});
		assertEquals(50, wallet.getAmount());
		assertEquals(2, wallet.getTransactions().size());

		// Test registerReceipt with valid amount
		wallet.registerReceipt(30, "Receipt for sale");
		assertEquals(80, wallet.getAmount());
		assertEquals(3, wallet.getTransactions().size());

		// Test registerReceipt with invalid amount
		assertThrows(IllegalArgumentException.class, () -> {
			wallet.registerReceipt(0, "Invalid receipt");
		});
		assertEquals(80, wallet.getAmount());
		assertEquals(3, wallet.getTransactions().size());
	}

}
