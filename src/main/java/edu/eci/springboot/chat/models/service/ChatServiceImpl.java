package edu.eci.springboot.chat.models.service;

import edu.eci.springboot.chat.models.documents.Message;
import edu.eci.springboot.chat.models.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements IChatService{

    @Autowired
    private IChatRepository chatRepository;
    @Override
    public List<Message> getLast10() {
        return chatRepository.findFirst10ByOrderByDateDesc();
    }

    @Override
    public Message save(Message message) {
        return chatRepository.save(message);
    }
}
