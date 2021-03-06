# Spring-Config-Client
Spring Config Client membutuhkan Spring Config Server untuk mendapatkan konfigurasinya, yaitu pada saat aplikasi client hidup dan berdasarkan property yang ada di client, maka client akan berusaha untuk meminta konfigurasi di Spring Config Server berdasarkan nama aplikasi. Nama aplikasi di sini berperan secara krusial

# Dependencies
Config Client</br>
Actuator</br>
Web</br>

# How to
1. Tidak ada penambahan anotation pada SpringApplication Class
2. Untuk mengetest konfigurasi dari sisi client terdapat 2 hal yang perlu di pertimbangkan</br>
a. Konfigurasi ke load dari Server Config dan terkirim ke client</br>
b. Konfigurasi dapat di reload bila terdapat perubahan dari konfigurasi</br>
3. Buat bootstrap.yml, sebagai pengganti application.properties
```
---
spring:
  application:
    name: SpringConfigClient
  cloud:
    config:
      uri: http://localhost:9080
      failFast: false
	
```
Konfigurasi di atas menjelaskan, bahwa aplikasi ini memiliki nama SpringConfigClient yang akan di mapping dengan konfigurasi file yang akan di ambil oleh Spring Config Server dan ambil konfigurasi tersebut di alamat yang sudah di tentukan. FailFast menandakan apakah aplikasi tetap berjalan bisa server config down</br>True : server config wajib hidup. </br>False : tetap jalan meski server config tidak hidup</br>

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

# Note
Bila client tidak menggunakan spring, maka configurasi dapat di akses dengan mengarahkan url ke Spring Cloud Server</br>
```http://{spring-config-server}:{port}/{client-app-name}/{profile}```</br>
Untuk konfigurasi app ini please refer to ```https://github.com/elvinotan/config/SpringConfigClient.yml``` </br>
Contoh lain ```https://github.com/kennyk65/Microservices-With-Spring-Student-Files/tree/master/lab-3/client-solution```</br>

  
