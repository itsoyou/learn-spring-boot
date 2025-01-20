package com.soyoung.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

	// "/say-hello" -> "Hello! What are you learning today?"

	@RequestMapping("say-hello")
	@ResponseBody // if you return String, by default Spring will looking for a View with that
					// name.
	// response body annotation will return whatever the function returns to the
	// browser.
	public String sayHello() {
		return "Hello! What are you learning today?";
	}

	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My First HTML Page - Changed</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	// JSP: Java Server Page. One of the most popular/earliest View tool.

	// "say-hello-jsp" => sayHello.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp

	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		// returning the name of JSP. Spring MVC is using View Resolver (suffix/prefix
		// env vars)
		return "sayHello";
	}
}
