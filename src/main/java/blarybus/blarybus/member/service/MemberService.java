package blarybus.blarybus.member.service;

import blarybus.blarybus.global.exception.CustomException;
import blarybus.blarybus.global.exception.ErrorCode;
import blarybus.blarybus.global.jwt.JwtAuthService;
import blarybus.blarybus.global.jwt.dto.JwtTokenSet;
import blarybus.blarybus.global.response.ResponseService;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.member.domain.Member;
import blarybus.blarybus.member.domain.dto.MemberJoinReq;
import blarybus.blarybus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtAuthService jwtAuthService;


    public SingleResult<JwtTokenSet> join(MemberJoinReq req) {
        Optional<Member> findMember = memberRepository.existLoginId(req.loginId());
        if (findMember.isPresent()) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }

        if (!Objects.equals(req.password(), req.password2())) {
            throw new CustomException(ErrorCode.USER_PASSWORD_NOTEQUAL);
        }

        Member newMember = Member.builder()
                .memberId(UUID.randomUUID().toString())
                .password(req.password())
                .name(req.name())
                .loginId(req.loginId())
                .build();

        memberRepository.save(newMember);

        JwtTokenSet jwtTokenSet = jwtAuthService.generateToken(newMember.getMemberId());

        return ResponseService.getSingleResult(jwtTokenSet);
    }
}
