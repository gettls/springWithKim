package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;

class OrderServiceImplTest {
	
	@Test
	void createOrder() {
		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));
		
		OrderServiceImpl orderService = new OrderServiceImpl(new FixDiscountPolicy(), memberRepository);
		Order order = orderService.createOrder(1L, "itemA0", 10000);
	
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(10000);
	}
}
