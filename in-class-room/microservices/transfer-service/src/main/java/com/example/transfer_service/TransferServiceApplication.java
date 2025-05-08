package com.example.transfer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
class TransferRequest {
	private String fromAccount;
	private String toAccount;
	private double amount;
}

@Data
class TransferResponse {
	private String status;
	private String message;
}

@Data
class Account {
	private String number;
	private double balance;
}


@SpringBootApplication
@RestController
public class TransferServiceApplication {

	private RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping(
		value="/transfer",
		consumes="application/json",
		produces="application/json"
	)
	public TransferResponse transfer(@RequestBody TransferRequest request) {
		// Simulate a transfer operation

		// Api call, to get account details
		String apiUrl = "http://localhost:8181/api/accounts/" + request.getFromAccount(); // service discovery patter
		Account account = restTemplate.getForObject(apiUrl, Account.class); // sync http-call

		if (account.getBalance() < request.getAmount()) {
			TransferResponse response = new TransferResponse();
			response.setStatus("failure");
			response.setMessage("Insufficient balance in account " + request.getFromAccount());
			return response;
		}
		
		// debit from account
		// credit to account

		// Message to be sent to the message broker ( e.g., RabbitMQ, Kafka)


		String message = "Transferred " + request.getAmount() + " from " + request.getFromAccount() + " to "
				+ request.getToAccount();
		kafkaTemplate.send("notifications", message);

		TransferResponse response = new TransferResponse();
		response.setStatus("success");
		response.setMessage("Transferred " + request.getAmount() + " from " + request.getFromAccount() + " to " + request.getToAccount());
		return response;
	}


	public static void main(String[] args) {
		SpringApplication.run(TransferServiceApplication.class, args);
	}

}
