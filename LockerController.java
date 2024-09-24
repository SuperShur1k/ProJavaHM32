package com.example.locker220124.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LockerController {
    @GetMapping("/open")
    public String open() {
        return "open";
    }


// HomeWork
//    Создайте в контроллере новый метод music, принимающий строку и возвращающий эту строку в верхнем регистре
//    Разрешите выполение этого метода контроллера только членам группу ARTISTS
    @PreAuthorize("hasRole('ROLE_ARTISTS')")
    @GetMapping("/music/{music}")
    public String music(
        @PathVariable String music
    ) {
        return music.toUpperCase();
    }

    // @PreAuthorize("hasRole('ROLE_USER')") // только те у кого есть роль нужная
    @GetMapping("/secure")
    public String secure() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("secure,  user: {}, group: {}", auth.getName(), auth.getAuthorities());
        return "secure";
    }
}
