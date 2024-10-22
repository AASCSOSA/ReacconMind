package com.reacconmind.reacconmind.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Message")
public class Message {

    @Id
    private int idMessage; 
    private int idSender; 
    private int idAddressee; 
    private String content;     
    private String multimedia;
    private String shippingDate; 

    public Message() {}
    
    public Message(int idMessage, int idSender, int idAddressee, String content, String multimedia, String shippingDate) {
        this.idMessage = idMessage;
        this.idSender = idSender;
        this.idAddressee = idAddressee;
        this.content = content;
        this.multimedia = multimedia;
        this.shippingDate = shippingDate;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdAddressee() {
        return idAddressee;
    }

    public void setIdAddressee(int idAddressee) {
        this.idAddressee = idAddressee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }




}
