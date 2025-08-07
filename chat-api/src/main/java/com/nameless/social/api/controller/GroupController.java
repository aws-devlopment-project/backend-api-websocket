package com.nameless.social.api.controller;

import com.nameless.social.api.model.GroupModel;
import com.nameless.social.api.response.CommonResponse;
import com.nameless.social.api.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupController {
	private final GroupService groupService;

	// TODO RESTful API로 수정
	// TODO 가능하면 TDD...
	@Operation(summary = "사용자가 가입한 그룹 조회")
	@GetMapping("/user/getUserJoin")
	public CommonResponse<GroupModel> getGroupListByUserEmail(
			final HttpServletRequest request, // TODO ArgumentResolver로 Authorization 인증확인하도록 하기 -> final User user
			@RequestParam(value = "email", required = false) final String email
	) {
		// TODO Email을 클라이언트에서 가지고 있기 때문에 서버에서 또 이베일을 전달해줄 필요가 없음.
		// TODO 그리고 헤더에 인증 토큰을 보내기 때문에 email을 전달할 필요가 없음.
		return CommonResponse.success(groupService.getGroupByUserEmail(email));
	}

	@Operation(summary = "그룹 정보 조회")
	@GetMapping("/group/getGroupInfo")
	public CommonResponse<Object> getGroupInfo(
			final HttpServletRequest request,
			@RequestParam(value = "name", required = false) final String groupName
	) {
		return CommonResponse.success(groupService.getGroupInfo(groupName));
	}

	@Operation(summary = "전체 그룹 목록")
	@GetMapping("/group/getGroupList")
	public CommonResponse<Object> getGroupList(final HttpServletRequest request) {
		return CommonResponse.success(groupService.getGroupList());
	}
}
