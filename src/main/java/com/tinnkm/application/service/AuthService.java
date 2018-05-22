package com.tinnkm.application.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AuthService {
    boolean getAuth(String redirectUri,String state) throws IOException, URISyntaxException;
}
