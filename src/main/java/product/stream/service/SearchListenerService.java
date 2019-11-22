package product.stream.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import product.stream.data.ProductRepository;
import product.stream.model.Product;

@Service
public class SearchListenerService {

	@Autowired
	private ProductRepository productRepository;

	@KafkaListener(groupId = "json", topics = "product-with-price")
	public void processEvent(List<Product> products) {
		productRepository.saveAll(products);
	}

}
