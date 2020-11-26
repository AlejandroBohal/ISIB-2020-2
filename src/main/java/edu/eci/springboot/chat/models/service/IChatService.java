package edu.eci.springboot.chat.models.service;

import edu.eci.springboot.chat.models.documents.Message;

import java.util.List;

public interface IChatService {
    public List<Message> getLast10();
    public Message save(Message message);

}
