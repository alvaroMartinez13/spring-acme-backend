package com.project.spring_acme_backend.Services.Likes;

import com.project.spring_acme_backend.Entities.Likes;
import com.project.spring_acme_backend.Entities.Posts;

public interface LikeService {
 
    //Agregar un Like o me gusta a una publicación
    Likes addLike(Posts post, String username);

    //Eliminar like de la publicación, si el usuario dio like, puede dar dislike
    Likes deleteLike(Posts post, String username);

    //Obtener la cantidad de likes que tiene una publicación
    int countLikesByPost(Posts post);

}
