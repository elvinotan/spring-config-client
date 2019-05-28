# spring-config-client
Spring Config Client membutuhkan Spring Config Server, yaitu pada saat aplikasi client hidup dan berdasarkan property yang ada di client, maka client akan berusaha untuk meminta konfigurasi di Spring Config Server berdasarkan nama aplikasi. Nama aplikasi di sini berperan secara krusial

# dependencies
Config Client</br>
Actuator</br>
Web</br>

# how to
1. Tidak ada penambahan anotation pada SpringApplication Class
2. Untuk mengetest konfigurasi dari sisi client terdapat 2 hal yang perlu di pertimbangkan</br>
a. Konfigurasi ke load dari Server Config dan terkirim ke client</br>
b. Konfigurasi dapat di reload bila terdapat perubahan dari konfigurasi</br>
3. Buat bootstrap.properties, selevel dengan application.properties
```
spring.application.name=spring-cloud-client
spring.cloud.config.uri=http://localhost:9080
spring.cloud.config.failFast=false	
```
Konfigurasi di atas menjelaskan, bahwa aplikasi ini memiliki nama spring-cloud-client yang akan di mapping dengan konfigurasi file yang akan di ambil oleh Spring Config Server dan ambil konfigurasi tersebut di alamat yang sudah di tentukan. FailFast menandakan apakah aplikasi tetap berjalan bisa server config down(true:server config wajib hidup, false: tetap jalan meski server config tidak hidup)

4. Untuk memudahkan, kita akan membuat RestController sebagai media testing
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
```@RefreshScope``` : Setiap ada property yang dapat di reload ulang tambahkan anotation ini</br>
```@Value("${app.test.message:Default Test Message}")``` : Property yang akan di ambil dari Config Server dan yang akan di reload bila terdapat perubahan</br>
```POST : http://localhost:9081/actuator/refresh```: Bila konfigurasi berubah, perubahan tersebut tidak otomatis di terima oleh client, kita harus menjalakan rest command agar client meminta kembali konfigurasinya. endpoint /actuator/refresh ini otomatis di sertai pada saat kita include actuator dependencies</br>. Tapi ingat harus ada konfigurasi ```management.endpoints.web.exposure.include: refresh```agar endpoint tersebut dapat di akses

# note
Bila client tidak menggunakan spring, maka configurasi dapat di akses dengan mengarahkan url ke Spring Cloud Server</br>
```http://{spring-clound-server}:{port}/{client-app-name}/{profile}```
  
