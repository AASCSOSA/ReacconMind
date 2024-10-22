package com.reacconmind.reacconmind.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.dto.BotDTO;
import com.reacconmind.reacconmind.model.Bot;
import com.reacconmind.reacconmind.repository.BotRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BotService {

    @Autowired
    private BotRepository repository;


    public List<Bot> getAllBots() {
        return repository.findAll();
    }

    public Optional<Bot> getBotById(int idBot) {
        return repository.findByIdBot(idBot);
    }

    public Bot createBot(Bot bot) {
        return repository.save(bot);
    }

    public Bot updateBot(int idBot, Bot updatedBot) {
        Optional<Bot> existingBot = repository.findByIdBot(idBot);
        if (existingBot.isPresent()) {
            Bot bot = existingBot.get();
            bot.setName(updatedBot.getName());
            bot.setTheme(updatedBot.getTheme());
            //bot.setMultimedia(updatedBot.getMultimedia());
            return repository.save(bot);
        }
        return null;
    }

    public void deleteBot(int idBot) {
        repository.deleteById(idBot);
    }

    // Conversión de Entity a DTO
    public BotDTO convertEntityToDTO(Bot bot) {
        BotDTO dto = new BotDTO();
        dto.setIdBot(bot.getIdBot());
        dto.setName(bot.getName());
        dto.setTheme(bot.getTheme());
        dto.setShippingDate(Timestamp.valueOf(LocalDateTime.now())); // Aquí puedes manejar el campo shippingDate según lo necesites
        return dto;
    }

    // Conversión de DTO a Entity
    public Bot convertDTOToEntity(BotDTO dto) {
        Bot bot = new Bot();
        bot.setIdBot(dto.getIdBot());
        bot.setName(dto.getName());
        bot.setTheme(dto.getTheme());
        // El campo shippingDate no está en la entidad, así que no se asigna
        return bot;
    }


    // @Autowired
    // private MultimediaRepository multimediaRepository;

/*     public void save(Bot bot) {
        repository.save(bot);
    }

    public List<BotDTO> getAll() {
        List<Bot> bots = repository.findAll();
         return bots.stream()
                 .map(this::convertToDTO)
                 .collect(Collectors.toList());

         //return repository.findAll();
    }

    public Bot getBotById(Integer idBot) {
        return repository.findById(idBot).get();
    }
*/




    /*
     * public Bot updateBot(int idBot, Bot updatedBot) {
     * if (updatedBot.getName() == null || updatedBot.getTheme() == null) {
     * throw new
     * IllegalArgumentException("El nombre y el tema no pueden ser nulos");
     * }
     * 
     * return repository.findById(idBot).map(bot -> {
     * bot.setName(updatedBot.getName());
     * bot.setTheme(updatedBot.getTheme());
     * bot.setMultimedia(updatedBot.getMultimedia());
     * return repository.save(bot);
     * }).orElseThrow(() -> new
     * IllegalArgumentException("Bot no encontrado con ID: " + idBot));
     * }
     

    public void deleteBot(int idBot) {
        if (repository.existsById(idBot)) {
            repository.deleteById(idBot);
        } else {
            throw new IllegalArgumentException("Bot no encontrado con ID: " + idBot);
        }
    }

    public BotDTO convertToDTO(Bot bot) {
        return new BotDTO(
        bot.getName(),
        bot.getTheme(),
        Timestamp.valueOf(LocalDateTime.now())
        );
        // dto.setIdMultimedia(bot.getMultimedia() != null ?
        // bot.getMultimedia().getIdMultimedia() : 0); // Evitar null
        
    }

    public Bot convertToEntity(BotDTO dto) {
        return new Bot(
        dto.getName(),
        dto.getTheme() 
        );
*/






        // Obtener multimedia por su ID
        /*
         * Multimedia multimedia =
         * multimediaRepository.findById(dto.getIdMultimedia()).orElse(null);
         * bot.setMultimedia(multimedia);
         */

        
    
}
