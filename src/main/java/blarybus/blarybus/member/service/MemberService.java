package blarybus.blarybus.member.service;

import blarybus.blarybus.global.exception.CustomException;
import blarybus.blarybus.global.exception.ErrorCode;
import blarybus.blarybus.global.jwt.JwtAuthService;
import blarybus.blarybus.global.jwt.dto.JwtTokenSet;
import blarybus.blarybus.global.response.ResponseService;
import blarybus.blarybus.global.response.SingleResult;
import blarybus.blarybus.member.domain.Member;
import blarybus.blarybus.member.domain.dto.MemberJoinReq;
import blarybus.blarybus.member.domain.dto.MemberLoginReq;
import blarybus.blarybus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder passwordEncoder;

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
                .password(passwordEncoder.encode(req.password()))
                .name(req.name())
                .loginId(req.loginId())
                .build();

        memberRepository.save(newMember);

        JwtTokenSet jwtTokenSet = jwtAuthService.generateToken(newMember.getMemberId());

        return ResponseService.getSingleResult(jwtTokenSet);
    }

    public SingleResult<JwtTokenSet> login(MemberLoginReq req) {
        Optional<Member> findMember = memberRepository.existLoginId(req.loginId());
        if (findMember.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_EXIST);
        }

        Member member = findMember.get();
        if (!passwordEncoder.matches(req.password(), member.getPassword())) {
            throw new CustomException(ErrorCode.USER_WRONG_PASSWORD);
        }


        JwtTokenSet jwtTokenSet = jwtAuthService.generateToken(member.getMemberId());
        return ResponseService.getSingleResult(jwtTokenSet);
    }
}
