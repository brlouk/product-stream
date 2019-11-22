package product.stream.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import product.stream.model.ProductPrice;
import product.stream.service.ProductPriceService;

@RestController
@RequestMapping("/api/product/price")
public class ProductPriceController {

	@Autowired
	private ProductPriceService productPriceService;

	@PostMapping
	public ProductPrice add(@RequestBody ProductPrice productPrice) {
		productPriceService.sendToTopic(productPrice);
		return productPrice;
	}

}
