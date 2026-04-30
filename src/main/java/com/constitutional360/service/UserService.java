package com.constitutional360.service;

import com.constitutional360.dto.ProfileUpdateDTO;
import com.constitutional360.dto.UserDTO;
import com.constitutional360.entity.User;
import com.constitutional360.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO updateProfile(String email, ProfileUpdateDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null && !dto.getEmail().equals(email)) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("Email already taken");
            }
            user.setEmail(dto.getEmail());
        }
        if (dto.getProfilePic() != null) user.setProfilePic(dto.getProfilePic());

        user = userRepository.save(user);
        return toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name().replace("ROLE_", "").toLowerCase())
                .avatar(user.getAvatar())
                .streak(user.getStreak())
                .points(user.getPoints())
                .quizzesCompleted(user.getQuizzesCompleted())
                .articlesRead(user.getArticlesRead())
                .joinedDate(user.getJoinedDate() != null ? user.getJoinedDate().toString() : null)
                .profilePic(user.getProfilePic())
                .timeSpent(user.getTimeSpent())
                .build();
    }
}
