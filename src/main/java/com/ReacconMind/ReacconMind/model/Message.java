// package com.ReacconMind.ReacconMind.model;

// import java.sql.Date;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;

// @Entity
// public class Message {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int idMessage;
//     @ManyToOne
//     @JoinColumn(name = "idUser", nullable = false)
//     private  User sender; //Preguntar
//     @ManyToOne
//     @JoinColumn(name = "idUser", nullable = false)
//     private  User addressee; //Preguntar
//     private String content;
//     private String multimedia;
//     private Date shippingDate;

//     //llave primaria compuesta

//     public int getIdMessage() {
//         return idMessage;
//     }
//     public void setIdMessage(int idMessage) {
//         this.idMessage = idMessage;
//     }
//     public User getSender() {
//         return sender;
//     }
//     public void setSender(User sender) {
//         this.sender = sender;
//     }
//     public User getAddressee() {
//         return addressee;
//     }
//     public void setAddressee(User addressee) {
//         this.addressee = addressee;
//     }
//     public String getContent() {
//         return content;
//     }
//     public void setContent(String content) {
//         this.content = content;
//     }
//     public String getMultimedia() {
//         return multimedia;
//     }
//     public void setMultimedia(String multimedia) {
//         this.multimedia = multimedia;
//     }
//     public Date getShippingDate() {
//         return shippingDate;
//     }
//     public void setShippingDate(Date shippingDate) {
//         this.shippingDate = shippingDate;
//     }


    
// }
