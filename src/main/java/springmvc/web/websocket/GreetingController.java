package springmvc.web.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import springmvc.web.view.Greeting;
import springmvc.web.view.HelloMessage;

@Controller
public class GreetingController {
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message){
		return new Greeting("hello,"+message.getName());
	}
}
