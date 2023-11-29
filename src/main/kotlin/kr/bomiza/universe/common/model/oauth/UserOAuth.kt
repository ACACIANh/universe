package kr.bomiza.universe.common.model.oauth

import kr.bomiza.universe.domain.user.User
import org.springframework.security.oauth2.core.user.OAuth2User
import java.io.Serializable

class UserOAuth(
    // provider 에서 제공하는 데이터
    private val oAuth2User: OAuth2User,
    // provider 에서 제공하는 데이터를 재가공한 데이터
    private val kakaoUser: KakaoUser,
    // 도메인에서 사용하는 데이터
    private val user: User
) : OAuth2User by oAuth2User, Serializable {

    val identifier: String
        get() = user.email
}