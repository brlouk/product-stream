package product.stream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import product.stream.model.ProductInfo;

@Service
public class ProductInfoService {

	@Autowired
	KafkaTemplate<String, ProductInfo> kafkaTemplate;

	public void sendToTopic(ProductInfo productInfo) {
		kafkaTemplate.send("product", productInfo.getId(), productInfo);
	}

}
