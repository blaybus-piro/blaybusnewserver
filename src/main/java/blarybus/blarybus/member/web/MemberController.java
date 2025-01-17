package blarybus.blarybus.member.web;

import blarybus.blarybus.global.jwt.dto.JwtTokenSet;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.global.response.SuccessResponse;
import blarybus.blarybus.member.domain.dto.MemberJoinReq;
import blarybus.blarybus.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원(Member)")
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public SuccessResponse<SingleResult<JwtTokenSet>> join(@Valid @RequestBody MemberJoinReq req) {
        SingleResult<JwtTokenSet> result = memberService.join(req);
        return SuccessResponse.ok(result);
    }
}
