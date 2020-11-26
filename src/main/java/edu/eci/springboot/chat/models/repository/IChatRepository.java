package edu.eci.springboot.chat.models.repository;

import edu.eci.springboot.chat.models.documents.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IChatRepository extends MongoRepository<Message,String> {
    public List<Message> findFirst10ByOrderByDateDesc();
}
