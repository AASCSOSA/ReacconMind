package com.reacconmind.reacconmind.service;

import java.util.List;

import com.reacconmind.reacconmind.dto.BotDTO;
import com.reacconmind.reacconmind.model.ThemeBotType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.model.Bot;
import com.reacconmind.reacconmind.repository.BotRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BotService {

    @Autowired
    private BotRepository repository;

    // @Autowired
    // private MultimediaRepository multimediaRepository;

    public void save(Bot bot) {
        repository.save(bot);
    }

    public List<Bot> getAll() {
        return repository.findAll();
    }

    public Bot getBotById(Integer idBot) {
        return repository.findById(idBot).get();
    }

    /*public Bot updateBot(int idBot, Bot updatedBot) {
        if (updatedBot.getName() == null || updatedBot.getTheme() == null) {
            throw new IllegalArgumentException("El nombre y el tema no pueden ser nulos");
        }

        return repository.findById(idBot).map(bot -> {
            bot.setName(updatedBot.getName());
            bot.setTheme(updatedBot.getTheme());
            bot.setMultimedia(updatedBot.getMultimedia());
            return repository.save(bot);
        }).orElseThrow(() -> new IllegalArgumentException("Bot no encontrado con ID: " + idBot));
    }*/

    public void deleteBot(int idBot) {
        if (repository.existsById(idBot)) {
            repository.deleteById(idBot);
        } else {
            throw new IllegalArgumentException("Bot no encontrado con ID: " + idBot);
        }
    }

    // Conversión de Bot a BotDTO
    public BotDTO convertToDTO(Bot bot) {
        BotDTO dto = new BotDTO();
        dto.setIdBot(bot.getIdBot());
        dto.setName(bot.getName());
        dto.setTheme(bot.getTheme().name()); // Convertir Enum a String
        //dto.setIdMultimedia(bot.getMultimedia() != null ? bot.getMultimedia().getIdMultimedia() : 0); // Evitar null
        return dto;
    }

    // Conversión de BotDTO a Bot
    public Bot convertToEntity(BotDTO dto) {
        Bot bot = new Bot();
        bot.setIdBot(dto.getIdBot());
        bot.setName(dto.getName());
        bot.setTheme(ThemeBotType.valueOf(dto.getTheme())); // Convertir String a Enum

        // Obtener multimedia por su ID
        /*Multimedia multimedia = multimediaRepository.findById(dto.getIdMultimedia()).orElse(null);
        bot.setMultimedia(multimedia);*/

        return bot;
    }
}