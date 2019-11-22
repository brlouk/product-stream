package product.stream.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import product.stream.model.ProductInfo;
import product.stream.model.ProductPrice;
import product.stream.service.ProductInfoService;
import product.stream.service.ProductPriceService;

@RestController
@RequestMapping("/api/product/sample")
public class ProductSampleController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductSampleController.class);


	@Autowired
	private ProductInfoService productInfoService;

	@Autowired
	private ProductPriceService productPriceService;

	@PostMapping
	public void generateSample(@RequestParam long size) {
		
		log.info("Generate Sample of {}", size);

		for (long i = 0; i < size; i++) {
			
			// INFO
			ProductInfo info = new ProductInfo();
			info.setDescription(String.format("Description of product '%s'", i));
			info.setId(String.valueOf(i));
			info.setTitle(String.format("Sample product '%s'", i));
			productInfoService.sendToTopic(info);
			
			//PRICE
			ProductPrice price= new ProductPrice();
			price.setId(String.valueOf(i));
			price.setPrice((Math.random() * ((1000 - 10) + 1)) + 10);
			productPriceService.sendToTopic(price);
		}

	}

}
