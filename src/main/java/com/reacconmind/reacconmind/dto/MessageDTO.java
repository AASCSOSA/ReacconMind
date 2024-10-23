package com.reacconmind.reacconmind.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDTO {
    private int idMessage;
    private int idSender;
    private int idAddressee;
    private String content;
    private String multimedia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shippingDate;

    public MessageDTO() {}

    public MessageDTO(int idMessage, int idSender, int idAddressee, String content, String multimedia, LocalDateTime shippingDate) {
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

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }
}
