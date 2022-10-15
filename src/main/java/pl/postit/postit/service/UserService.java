package pl.postit.postit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.postit.postit.dto.UserDTO;
import pl.postit.postit.dto.UserRequestDTO;
import pl.postit.postit.model.StatusAccount;
import pl.postit.postit.model.User;
import pl.postit.postit.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserById(Long id) {
        return UserDTO.fromUser(userRepository.getReferenceById(id));
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    public UserDTO createUser(UserRequestDTO createRequest) {
        User user = User.builder().username(createRequest.getUsername()).password(createRequest.getPassword()).email(createRequest.getEmail()).dateOfBirth(createRequest.getDateOfBirth()).statusAccount(StatusAccount.OFFLINE).build();

        return UserDTO.fromUser(userRepository.save(user));
    }

    public UserDTO updateUserById(Long id, UserRequestDTO updatedInformation) {
        Optional<User> searchedUserOptional = userRepository.findById(id);
        if (searchedUserOptional.isPresent()) {
            User user = searchedUserOptional.get();

            user.setPassword(updatedInformation.getPassword());
            user.setEmail(updatedInformation.getEmail());
            user.setUsername(updatedInformation.getUsername());
            user.setDateOfBirth(updatedInformation.getDateOfBirth());

            return UserDTO.fromUser(userRepository.save(user));
        }
        throw new EntityNotFoundException("Didn't find user with id: " + id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
