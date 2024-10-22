package com.reacconmind.reacconmind.service;

import com.reacconmind.reacconmind.model.Comment;
import com.reacconmind.reacconmind.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    // Método para obtener todos los comentarios
    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    // Método para obtener un comentario por su ID
    public Comment getCommentById(Integer id) {
        Optional<Comment> optionalComment = repository.findById(id);
        return optionalComment.orElse(null); // Retorna null si no se encuentra el comentario
    }

    // Método para guardar un nuevo comentario o actualizar uno existente
    public void saveComment(Comment comment) {
        repository.save(comment);
    }

    // Método para eliminar un comentario por su ID
    public void deleteComment(Integer id) {
        repository.deleteById(id);
    }
}
