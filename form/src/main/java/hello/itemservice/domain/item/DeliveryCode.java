package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
/*
 * FAST : �������
 * NORMAL : �Ϲݹ��
 * SLOW : ���� ���
 */
@Data
@AllArgsConstructor
public class DeliveryCode {

	private String code;
	private String displayName;
	
}