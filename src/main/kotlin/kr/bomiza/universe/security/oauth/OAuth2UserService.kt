package kr.bomiza.universe.security.oauth

import kr.bomiza.universe.common.model.oauth.UserOAuth
import kr.bomiza.universe.domain.user.User
import kr.bomiza.universe.domain.user.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

/**
 * OAuth login 성공 후 로직
 */
@Service
class OAuth2UserService(
    val userRepository: UserRepository,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        // provider 에서 데이터 가져옴
        val oAuth2User = super.loadUser(userRequest)

        // provider 별 기준 속성
        val userNameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val providerUser = OAuth2ProviderUser.of(userNameAttributeName, oAuth2User.attributes)

        val user = saveOrUpdate(providerUser)

        // role 넘어가는지 확인 필요
        return UserOAuth(oAuth2User, providerUser.kakaoUserInfo, user)
    }

    private fun saveOrUpdate(providerUser: OAuth2ProviderUser): User {
        val user = userRepository.findByEmail(providerUser.kakaoUserInfo.kakaoAccount.email)
            .map { e ->
                // 책임? 메소드 분리?
                e.update(
                    providerUser.kakaoUserInfo.kakaoAccount.profile.nickname,
                    providerUser.kakaoUserInfo.properties.profileImage,
                )
            }
            .orElse(providerUser.toEntity())

        return userRepository.save(user)
    }
}