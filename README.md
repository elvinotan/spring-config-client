# spring-config-client
Spring Config Client membutuhkan Spring Config Server, yaitu pada saat aplikasi client hidup dan berdasarkan property yang ada di client, maka client akan berusaha untuk meminta konfigurasi di Spring Config Server berdasarkan nama aplikasi. Nama aplikasi di sini berperan secara krusial

# dependencies
Config Client
Actuator
Web

# how to
1. Tidak ada penambahan anotation pada SpringApplication Class
2. Untuk mengetest konfigurasi dari sisi client terdapat 2 hal yang perlu di pertimbangkan</br>
a. Konfigurasi ke load dari Server Config dan terkirim ke client</br>
b. Konfigurasi dapat di reload bila terdapat perubahan dari konfigurasi</br>
3. Buat bootstrap.properties, selevel dengan application.properties
```
spring.application.name=spring-cloud-client
spring.cloud.config.uri=http://localhost:9080
```
Konfigurasi di atas menjelaskan, bahwa aplikasi ini memiliki nama spring-clound-client yang akan di mapping dengan konfigurasi file yang akan di ambil oleh Spring Config Server dan ambil konfigurasi tersebut di alamat yang sudah di tentukan

4. Untuk memudahkan kita akan membuat RestController sebagai media testing
```
@RefreshScope
@RestController
@RequestMapping("/api")
public class ClientRest {
	
	@Value("${app.test.message:Default Test Message}")
	private String message;
	
	@GetMapping(value="/client")
	public String client() {
		return "Property app.test.message is "+message;
	}
}
```
```@RefreshScope``` : Setiap ada property yang dapat di reload ulang tambahkan anotation ini

  
