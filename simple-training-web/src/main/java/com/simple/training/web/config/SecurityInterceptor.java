package com.simple.training.web.config;

import static java.util.Objects.isNull;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.collect.ImmutableList;
import com.simple.training.domain.usuario.Usuario;
import com.simple.training.web.api.authentication.TokenAuthentication;
import com.simple.training.web.api.exception.AuthenticationException;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    
    public static final String TOKEN_HEADER = "Google-Token";

    /**
     * Client ID (ou ID do Cliente) da aplicação registrada no Google Developer
     * Console. Acessar https://console.developers.google.com/apis/credentials.
     */
    @Value("${application.simple-training.client-id}")
    private String CLIENT_ID;

    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws AuthenticationException, JSONException, IOException, GeneralSecurityException {

        String token = request.getHeader(TOKEN_HEADER);
        
        if (token == null) {
            throw new AuthenticationException("Token não informado");
        }

        authenticate(token);

        return true;
    }

    /**
     * Realiza a autenticação do usuário através de sua conta
     * no Google, utilizando o token recebido em um header
     * 
     * @param googleToken
     * @throws IOException
     * @throws JSONException256805
     * @throws AuthenticationException
     * @throws GeneralSecurityException 
     */
    private void authenticate(String googleToken) throws GeneralSecurityException, IOException, AuthenticationException {

        GoogleIdToken idToken = verifyForIssuer("accounts.google.com", googleToken);

        if (isNull(idToken)) {
            idToken = verifyForIssuer("https://accounts.google.com", googleToken);

            if (isNull(idToken)) {
                throw new AuthenticationException("Token inválido");
            }
        }

        Payload payload = idToken.getPayload();
        Usuario usuario = new Usuario();
        usuario.setEmail(payload.getEmail());
        usuario.setNome((String) payload.get("name"));
        Authentication auth = buildAuthentication(usuario, googleToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private GoogleIdToken verifyForIssuer(String issuer, String googleToken)
        throws GeneralSecurityException, IOException {

        return new GoogleIdTokenVerifier.Builder(TRANSPORT, JSON_FACTORY)
                        .setAudience(Arrays.asList(CLIENT_ID))
                        .setIssuer(issuer)
                        .build()
                        .verify(googleToken);
    }

    private Authentication buildAuthentication(Usuario usuario, String token) {

        GrantedAuthority authority = new SimpleGrantedAuthority("USUARIO");
        TokenAuthentication auth = new TokenAuthentication(usuario, token, ImmutableList.of(authority));
        return auth;
    }

}
