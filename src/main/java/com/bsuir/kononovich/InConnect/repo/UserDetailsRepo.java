package com.bsuir.kononovich.InConnect.repo;

import com.bsuir.kononovich.InConnect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository <User, String> {
}
