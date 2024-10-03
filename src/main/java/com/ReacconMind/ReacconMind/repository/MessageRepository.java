package com.ReacconMind.ReacconMind.repository; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ReacconMind.ReacconMind.model.Message;;;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
}
