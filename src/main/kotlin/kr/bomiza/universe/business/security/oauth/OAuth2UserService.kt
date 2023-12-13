package kr.bomiza.universe.business.security.oauth

import kr.bomiza.universe.domain.security.model.SecurityUser
import kr.bomiza.universe.business.security.domain.UserOAuth
import kr.bomiza.universe.business.security.web.SecurityUserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

/**
 * OAuth login 성공 후 로직
 */
@Service
class OAuth2UserService(
    val securityUserRepository: SecurityUserRepository,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        val oAuth2User = super.loadUser(userRequest)

        val clientProviderName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val providerUser = OAuth2ProviderUser.of(clientProviderName, oAuth2User.attributes)

        val user = saveOrUpdate(providerUser)

        return UserOAuth(oAuth2User, user)
    }

    private fun saveOrUpdate(providerUser: OAuth2ProviderUser): SecurityUser {
        val userContext = providerUser.userContext()
        return securityUserRepository.findByEmail(userContext.email)
            .map { existingUser ->
                existingUser.update(userContext)
                val updatedUser = securityUserRepository.save(existingUser)
                SecurityUserMapper.toDomain(updatedUser)
            }
            .orElseGet {
                val securityUser = providerUser.createUser()
                val userEntity = SecurityUserMapper.toEntity(securityUser, userContext)
                securityUserRepository.save(userEntity)
                securityUser
            }
    }
}