package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;


@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

	private int discountFixAmount = 1000; // 1000¿ø ÇÒÀÎ
	
	@Override
	public int discount(Member member, int pirce) {
		if(member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		}else {
			return 0;
		}
	}

}
