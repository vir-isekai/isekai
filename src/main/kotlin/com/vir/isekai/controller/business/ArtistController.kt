package com.vir.isekai.controller.business

import com.vir.isekai.common.security.UserPrincipal
import com.vir.isekai.domain.dto.CommonResponse
import com.vir.isekai.domain.dto.request.ArtistRequest
import com.vir.isekai.domain.dto.response.ArtistResponse
import com.vir.isekai.facade.ArtistFacade
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/artists")
class ArtistController(
	private val artistFacade: ArtistFacade,
) {
	@GetMapping("/{artistId}")
	fun getArtistById(
		@AuthenticationPrincipal userInfo: UserPrincipal,
		@PathVariable artistId: Long,
	): CommonResponse<ArtistResponse.Detail> {
		return CommonResponse.ok(artistFacade.getArtistById(artistId))
	}

	@PostMapping
	fun saveArtist(
		@RequestBody request: ArtistRequest.Save,
	): CommonResponse<Nothing> {
		artistFacade.saveArtist(request)

		return CommonResponse.successSave()
	}
}