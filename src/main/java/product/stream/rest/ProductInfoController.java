package product.stream.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import product.stream.model.ProductInfo;
import product.stream.service.ProductInfoService;

@RestController
@RequestMapping("/api/product/info")
public class ProductInfoController {

	@Autowired
	private ProductInfoService productInfoService;

	@PostMapping
	public ProductInfo save(@RequestBody ProductInfo productInfo) {
		productInfoService.sendToTopic(productInfo);
		return productInfo;
	}

}
