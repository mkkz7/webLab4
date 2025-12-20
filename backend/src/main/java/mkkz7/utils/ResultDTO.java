package mkkz7.utils;

public class ResultDTO {
    public boolean success;
    public String message;

    public ResultDTO(boolean success, String msg){
        this.success = success;
        this.message = msg;
    }
}
