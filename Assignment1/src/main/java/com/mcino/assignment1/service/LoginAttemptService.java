package com.mcino.assignment1.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 3;
    private LoadingCache<String, Integer> attemptsCache; // IP -> number of login attempts

    public LoginAttemptService(){
        super();
        // Store cache of login attempts for 24 hours
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String s) {
                return 0;
            }
        });
    }

    // Resets number of attempts for specified IP if login was successful before 3 login attempts
    public void loginSucceeded(String ip){
        attemptsCache.invalidate(ip);
    }

    // Caches the failed login attempts
    public void loginFailed(String ip){
        int attempts = 0;
        try{
            attempts = attemptsCache.get(ip);
        } catch (ExecutionException ignored) {
        }
        attempts++;
        attemptsCache.put(ip, attempts); // storing number of failed attempts for an IP address
    }

    public boolean isBlacklisted(String ip){
        try{
            return attemptsCache.get(ip) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }

}
