package ru.tusur.udo.sensors;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import ru.tusur.ru.sensors.camel.processors.EmulationProcessor;
import ru.tusur.ru.sensors.camel.processors.JSONProcessor;

@Component
public class SensorRoutes extends RouteBuilder {

	private JSONProcessor jProcessor;
	private EmulationProcessor emulationProcessor;
	
	public EmulationProcessor getEmulationProcessor() {
		return emulationProcessor;
	}

	public void setEmulationProcessor(EmulationProcessor emulationProcessor) {
		this.emulationProcessor = emulationProcessor;
	}

	public JSONProcessor getjProcessor() {
		return jProcessor;
	}

	public void setjProcessor(JSONProcessor jProcessor) {
		this.jProcessor = jProcessor;
	}

	@Override
	public void configure() throws Exception {
		from("timer://timer1?period=1000") 
        .process(this.emulationProcessor).to("direct:toJSon");
		
		
		from("direct:toJSon") 
        .process(this.jProcessor).to("file:d:/1.txt");
		
	}
		
}