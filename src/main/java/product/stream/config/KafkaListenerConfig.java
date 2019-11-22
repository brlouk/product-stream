//package product.stream.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import product.stream.model.Product;
//
//@Configuration
//public class KafkaListenerConfig {
//
//	@Value("${spring.kafka.bootstrap-servers}")
//	private String bootstrapServers;
//
//	@Bean
//	public Map<String, Object> consumerConfigs() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"100");
//		props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG,"100");
//		return props;
//	}
//	
//	@Bean
//    public ConsumerFactory<String, Product> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                consumerConfigs(),
//                new StringDeserializer(),
//                new JsonDeserializer<>(Product.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Product> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//}
