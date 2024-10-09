package com.ReacconMind.ReacconMind.service;

import java.util.List;

import com.ReacconMind.ReacconMind.model.Reply;
import com.ReacconMind.ReacconMind.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReplyService {

    @Autowired
    private ReplyRepository repo;

    // Obtener todas las respuestas (replies)
    public List<Reply> getAllReplies() {
        return repo.findAll();
    }

    // Guardar una nueva respuesta (reply)
    public void saveReply(Reply reply) {
        repo.save(reply);
    }

    // Obtener una respuesta (reply) por su ID
    public Reply getByIdReply(Integer idReply) {
        return repo.findById(idReply).orElse(null); // Devuelve null si no se encuentra
    }

    // Eliminar una respuesta (reply) por su ID
    public void deleteReply(Integer idReply) {
        repo.deleteById(idReply);
    }
}
