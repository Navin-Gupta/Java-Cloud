package com.capg.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer
public class OAuthSecurityConfiguration extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// Third party credential
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		clients.inMemory()
			   .withClient("client")
			   .authorizedGrantTypes("password", "autherization_code", "refresh_token", "implicit")
			   .scopes("read", "write")
			   .autoApprove(true)
			   .secret(this.passwordEncoder.encode("secretpass"));
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// configure the OAuth (Third Party) endpoints
		endpoints
			.authenticationManager(this.authenticationManager)
			.tokenStore(new InMemoryTokenStore()); // can be configured for DB Token Store
			
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// all request through OAuth Server needs to be authenticated
		security.checkTokenAccess("isAuthenticated()"); 
	}
	
	
	
}
