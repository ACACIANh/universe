package kr.bomiza.universe.security.oauth

import kr.bomiza.universe.common.util.GsonUtils
import kr.bomiza.universe.common.util.UUIDUtils
import kr.bomiza.universe.security.domain.Authorities
import kr.bomiza.universe.security.domain.Authority
import kr.bomiza.universe.security.domain.OAuthUserContext
import kr.bomiza.universe.security.domain.SecurityUser

// todo: kakaoUserInfo 대신 상위모델로 공통화
class OAuth2ProviderUser(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val kakaoUserInfo: KakaoUser,
) {
    companion object {
        fun of(userNameAttributeName: String, attributes: Map<String, Any>): OAuth2ProviderUser {

            return ofKakao(userNameAttributeName, attributes)
        }

        private fun ofKakao(userNameAttributeName: String, attributes: Map<String, Any>): OAuth2ProviderUser {

            val gson = GsonUtils.getLowerCaseWithUnderscores()

            val jsonValue = gson.toJson(attributes)

            val kakaoUserInfo = gson.fromJson(jsonValue, KakaoUser::class.java)

            return OAuth2ProviderUser(
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
                kakaoUserInfo = kakaoUserInfo
            )
        }
    }

    fun createUser(): SecurityUser {
        return SecurityUser(
            email = kakaoUserInfo.kakaoAccount.email,
            Authorities(Authority.MEMBER, Authority.ADMIN)
        )
    }

    fun userContext(): OAuthUserContext {
        return OAuthUserContext(
            kakaoUserInfo.kakaoAccount.email,
            kakaoUserInfo.kakaoAccount.profile.nickname,
            kakaoUserInfo.properties.profileImage
        )
    }
}