package kr.bomiza.universe.security.oauth

import kr.bomiza.universe.common.model.enums.UserRole
import kr.bomiza.universe.common.model.enums.UserState
import kr.bomiza.universe.common.model.oauth.KakaoUser
import kr.bomiza.universe.common.util.GsonUtils
import kr.bomiza.universe.domain.user.User

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

    fun toEntity(): User {
        return User(
            name = kakaoUserInfo.kakaoAccount.profile.nickname,
            email = kakaoUserInfo.kakaoAccount.email,
            picture = kakaoUserInfo.properties.profileImage,
            state = UserState.ACTIVATE,
            role = UserRole.MEMBER,
        )
    }
}