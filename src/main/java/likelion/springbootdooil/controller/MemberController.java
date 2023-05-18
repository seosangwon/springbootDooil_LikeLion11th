package likelion.springbootdooil.controller;


import likelion.springbootdooil.domain.Member;
import likelion.springbootdooil.service.MemberService;
import likelion.springbootdooil.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * 채우시오
 * Component Scan을 위한 @Controller 어노테이션 -> 생성자에 대한 AutoWire가 자동으로 된다
 * RequestMapping이 클래스 위에 명시되어 있으므로 url에 /members 라는 경로가 들어올 때는 클래스 내 헤당 메소드 들에게도
 * 모두 적용이 된다 예 : /members/new
 **/
@Controller
@RequestMapping("members")
public class MemberController {
    /*
     * 채우시오.
     * private final 로 선언한 이유는 불변이여야 하기 때문이다. MemberController에 필요한 파라미터인 memberServiceImpl은
     * 누군가에 의한 변형이 되지 않기 위해 초반 생성자 단계에서 필드내 선언하여 memberServiceImpl에 대한 의존성을 주입한다
     * 롬복에 @RequiredArgsConstructor 어노테이션을 통해 따로 생성자 메서드 생성 대신 밑 처럼 한 줄의 코드로 자동으로 생성 될 수 있다.
     *Service의 interface를 변수로 선언해줬다.
     * 역할과 구변이 명확해야 하기 때문에 나중에 구현체를 변경해줄 일이 있을 수도 있으니 약한 결합을 해준다.
     * 필드 선언은 인터페이스로 하고 생성자 단계에서 구현체인 ServiceImpl을 주입 받는걸 볼 수 있다
     **/
    private final MemberService memberService;

    /*
     * (예시)
     * 이 것은 생성자입니다.
     * @Autowired라는 어노테이션은 MemberController 객체를 실행해야 할 때 필요한 의존성을 주입해달라고 선언하기 위해 명시하는 어노테이션이며, 생성자 주입 방식을 선언하고 있습니다.
     * MemberController의 필드를 MemberService 타입으로 선언하였지만, 생성자 paramer에는 MemberServiceImpl이 주입되게 함으로써 느슨한 결합(Loosen Coupling)을 구현하였습니다.
     * @참고 : 실제로는 MemberController 생성자의 파라미터에 MemberServiceImpl이 아니라 MemberService로 쓰여있어도 스프링이 알아서 구현체 클래스의 인스턴스 (MemberServiceImpl memberserviceimpl)를 넣어주게 됩니다.
     *       즉, public MemberController(MemberService memberService) {this.memberService = memberService;} 와 같이 작성해도 에러가 없고, 이게 사실 정석입니다.
     *       아래처럼 작성해 둔 이유는, 실제로는 아래와 같이 동작한다는 것을 여러분 눈으로 먼저 보길 바랐던 제 마음이었습니다.
     *       지금, MemberController의 필드가 MemberService 타입의 데이터인데, 생성자로 주입되는 것은 MemberServiceImpl 타입이라는 것을 충분히 음미하시길 바랍니다.
     **/
    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

    /*
     * 채우시오
     * Model은 Controller와 View 사이에 데이터를 전달하는 객체
     * url에 new가 입력 받았을 때 , createForm 메소드가 실행 ,
     * Members 객체 형태로 memberFrom이라는 이름을 가지고 Controller가 View에게 넘겨준다
     * 그 후 members/createMEmberForm이라는 html 경로 로 return 해준다
     **/
    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/createMemberForm";
    }

    /*
     * 채우시오
     * url에 new라는 패스가 들어오면 , 해당 create 메소드를 실행
     * member 객체를  View로 부터 데이터 바인딩 과정 후 파라미터로 받아 Service의 비즈니스 로직을 이용하여 저장
     * 그 후 redirect를 하는데 현재 /로 명시되어 있기에 위에 RequestMapping("members")에 따라 /members View로 이동한다
     **/
    @PostMapping("new")
    public String create(Member member) {
        memberService.save(member);
        return "redirect:/";
    }

    /*
     * 채우시오
     * ""url이 들어왔을 때 findAll 메소드가 실행 : /members에 대한 url 요청
     * List 구조인 memberList를 선언하고, memberService의 로직인 findAll을 실행 해서 모두 저장
     * model.AddAttribute로 memberList type의 이름 memberList를 View에게 전달해준다
     * 실행 후 mebers/meberList html 파일 return
     **/
    @GetMapping("")
    public String findAll(Model model) {

        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "members/memberList";
    }


}
