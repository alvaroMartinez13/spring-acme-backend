package com.project.spring_acme_backend.Services.Posts;

import java.util.List;

import com.project.spring_acme_backend.DTO.PostDTO;
import com.project.spring_acme_backend.Entities.Posts;

public interface PostService {
    
    //Mostrar todas las publicaciones de los demás usuarios a los cuales el usuario sigue
    List<PostDTO> getAllPosts(String username);

    //Obtener una publicación
    PostDTO getPost(Long id);

    //Agregar una publicación
    int addPost(String username, Posts post);

    //Actualizar publicación
    int updatePost(Long id, String username, Posts post);

    //Eliminar publicación
    int deletePost(Long id); 

    //Obtener las publicaciones del usuario
    List<Posts> getMyPosts(String username);

    //Ordenar las publicaciones de los otros usuarios de manera descendiente
    List<Posts> postsOrderByDesc(String username);
    
    //Ordenar las publicaciones de los otros usuarios de manera ascendiente
    List<Posts> postsOrderByAsc(String username);

    //Ordenar las publicaciones de los otros usuarios de manera relevante
    /*Se calcula se calculará en función del número de reacciones y comentarios en la
      publicación dentro de un período determinado.*/
    List<Posts> postsOrderByRelevant(String username);

    //Agregar un Tag a una Publicación
    Boolean addTagToPost(Long postId, String tagName);

    //Eliminar un Tag de una Publicación
    Boolean removeTagFromPost(Long postId, Long tagId);

}
