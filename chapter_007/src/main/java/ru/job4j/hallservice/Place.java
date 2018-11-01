package ru.job4j.hallservice;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Place {
    private int row;
    private int placeNum;
    private Integer accountId;

    public Place(int row, int placeNum, Integer accountId) {
        this.row = row;
        this.placeNum = placeNum;
        this.accountId = accountId;
    }

    public Place(int row, int placeNum) {
        this.row = row;
        this.placeNum = placeNum;
        this.accountId = null;
    }

    public int getRow() {
        return row;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public Integer getAccount() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
