package com.tinnkm.application.service;

import com.tinnkm.application.model.Approval;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AuthService {
    Approval getAuth(String code, String state) throws IOException, URISyntaxException;
}
