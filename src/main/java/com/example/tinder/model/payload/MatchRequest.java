package com.example.tinder.model.payload;

public class MatchRequest {
    private Long idAnimal1;
    private Long idAnimal2;
    private boolean like;

    public MatchRequest(){

    }


    public Long getIdAnimal1() {
        return idAnimal1;
    }

    public void setIdAnimal1(Long idAnimal1) {
        this.idAnimal1 = idAnimal1;
    }

    public Long getIdAnimal2() {
        return idAnimal2;
    }

    public void setIdAnimal2(Long idAnimal2) {
        this.idAnimal2 = idAnimal2;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
