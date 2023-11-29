package kr.bomiza.universe.security.oauth

data class KakaoAccessToken(
    val access_token: String,
    val token_type: String,
    val refresh_token: String?,
    val expires_in: Int,
    val refresh_token_expires_in: Int?,
    val scope: String?
)

data class KakaoUser(
    val id: Long,
    val connectedAt: String,
    val properties: Properties,
    val kakaoAccount: KakaoAccount
) {
    data class Properties(
        val nickname: String,
        val profileImage: String,
        val thumbnailImage: String
    )

    data class KakaoAccount(
        val profileNicknameNeedsAgreement: Boolean,
        val profileImageNeedsAgreement: Boolean,
        val profile: Profile,
        val hasEmail: Boolean,
        val emailNeedsAgreement: Boolean,
        val isEmailValid: Boolean,
        val isEmailVerified: Boolean,
        val email: String
    ) {
        data class Profile(
            val nickname: String,
            val thumbnailImageUrl: String,
            val profileImageUrl: String,
            val isDefaultImage: Boolean
        )
    }
}
