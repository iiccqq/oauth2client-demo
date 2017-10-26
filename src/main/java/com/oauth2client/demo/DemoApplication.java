package com.oauth2client.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//OAuth2RestTemplate oauth2RestTemplate;

	/*@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
			OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}*/

	@RequestMapping("/")
	public RedirectView home() {

		String login = "http://localhost:8080/uaa/oauth/authorize?client_id=acme&state=state&response_type=code&redirect_uri=http://localhost:8088/index";
		return new RedirectView(login);

	}

	@RequestMapping("/index")
	public String index(String code, String state) {
		RestTemplate r = new RestTemplate();
		String url = "http://localhost:8080/uaa/oauth/token?client_id=acme&state=state&redirect_uri=http://localhost:8088/index&grant_type=authorization_code&code="
				+ code;
		String tokenInfo = r.postForObject(url, null, String.class);
		return tokenInfo;
	}

	/*@RequestMapping("/client")
	public String client() {

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();

		String url = "http://localhost:8081/service2";

	//	String result = oauth2RestTemplate.postForObject(url, "", String.class, new Object[] {});
		return "client" + result;
	}*/
}
