package product.stream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import product.stream.model.ProductPrice;

@Service
public class ProductPriceService {

	@Autowired
	KafkaTemplate<String, ProductPrice> kafkaTemplate;

	public void sendToTopic(ProductPrice productPrice) {
		kafkaTemplate.send("price", productPrice.getId(), productPrice);
	}

}
