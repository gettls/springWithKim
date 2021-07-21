package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//  private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	private DiscountPolicy discountPolicy;
	private MemberRepository memberRepository;
	
	@Autowired
	public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
		this.discountPolicy = discountPolicy;
		this.memberRepository = memberRepository;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

}
