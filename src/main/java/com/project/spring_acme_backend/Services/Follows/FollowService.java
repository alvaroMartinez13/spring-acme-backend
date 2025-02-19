package com.project.spring_acme_backend.Services.Follows;

import java.util.List;

import com.project.spring_acme_backend.Entities.Follows;
import com.project.spring_acme_backend.Entities.Users;

public interface FollowService {
    

    //Obtener los que sigo
    List<Users> getFollowing(String username);

    //Dejar de seguir
    String unFollowing(String username, String following);

    //Seguir al usuario
    Boolean following(String username, String following);

    //Obtener mis seguidores
    List<Follows> getFollowers(String username);

}
