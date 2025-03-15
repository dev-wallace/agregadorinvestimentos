package dev.wallace.agregadorinvestimentos.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "account_id")
     @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    
    public Account() {
    }


    public Account(UUID accountId, String description) {
        this.accountId = accountId;
        this.description = description;
    }


    public UUID getAccountId() {
        return accountId;
    }


    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    

}
