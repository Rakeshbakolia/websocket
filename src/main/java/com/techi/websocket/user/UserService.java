package com.techi.websocket.user;

import com.techi.websocket.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user){
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if(Objects.nonNull(storedUser)) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(user);
        }
    }

    public List<User> connectedUsers(){
        return repository.findAllByStatus(Status.ONLINE);
    }

}
