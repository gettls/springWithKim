package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

// "12.0.0.1:8008" => IpPort ��ü�� �ٲٱ�
@Getter
@EqualsAndHashCode
public class IpPort {
	private String ip;
	private int port;
	
	public IpPort(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
}
