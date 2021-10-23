package hello.typeconverter.converter;

import org.springframework.core.convert.converter.Converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String>{
	
	
	@Override
	public String convert(IpPort source) {
		log.info("convert source = {}",source);
		// ipport °´Ã¼ => "12.0.0.1:8080"
		return source.getIp() + ":" + source.getPort();
	}
	
}
