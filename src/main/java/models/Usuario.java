package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Usuario implements Serializable {

    private int id;
    private String email;
    private String pass;
    private boolean admin;

}
