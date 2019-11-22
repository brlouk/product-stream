package product.stream.config;

import java.util.function.BiFunction;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.fasterxml.jackson.databind.ObjectMapper;

import product.stream.model.Product;
import product.stream.model.ProductInfo;
import product.stream.model.ProductPrice;

@Configuration
public class KafkaStreamConfig {

	@Bean
	public BiFunction<KStream<String, ProductPrice>, KTable<String, ProductInfo>, KStream<String, Product>> priceStream() {

		ObjectMapper mapper = new ObjectMapper();
		Serde<Product> productSerde = new JsonSerde<>(Product.class, mapper);

		return (productPrice, productInfo) -> productPrice //
				.leftJoin(productInfo, (price, info) -> {
					if (info != null) {
						return new Product(info.getId(), info.getTitle(), info.getDescription(), price.getPrice());
					}
					return new Product(price.getId(), "unknown", "unknown", price.getPrice());
				})//
				.map((id, product) -> {
					return new KeyValue<>(id, product);
				})//
				.groupBy(
						(id, product) -> id,
						Grouped.with(null, productSerde))
				.reduce((product1, product2) -> {
					return product2;
				})//
				.toStream();//
	}
	
	@Bean
	public BiFunction<KStream<String, ProductInfo>, KTable<String, ProductPrice>, KStream<String, Product>> infoStream() {

		ObjectMapper mapper = new ObjectMapper();
		Serde<Product> productSerde = new JsonSerde<>(Product.class, mapper);

		return (productInfo, productPrice) -> productInfo //
				.leftJoin(productPrice, (info, price) -> {
					return new Product(info.getId(), info.getTitle(), info.getDescription(), price != null ? price.getPrice(): -1D);
				})//
				.map((id, product) -> {
					return new KeyValue<>(id, product);
				})//
				.groupBy(
						(id, product) -> id,
						Grouped.with(null, productSerde))
				.reduce((product1, product2) -> {
					return product2;
				})//
				.toStream();//
	}

}
