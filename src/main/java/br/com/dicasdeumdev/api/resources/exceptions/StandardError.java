package br.com.dicasdeumdev.api.resources.exceptions;

import java.time.LocalDateTime;

public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

    //Constructors

    public StandardError() {
    }

    public StandardError(LocalDateTime now, int status, String error, String path) {
        this.timestamp = now;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    // Getters and Setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // equals, hashCode and toString

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        StandardError that = (StandardError) o;
//
//        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
//        if (status != null ? !status.equals(that.status) : that.status != null) return false;
//        if (error != null ? !error.equals(that.error) : that.error != null) return false;
//        return path != null ? path.equals(that.path) : that.path == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = timestamp != null ? timestamp.hashCode() : 0;
//        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (error != null ? error.hashCode() : 0);
//        result = 31 * result + (path != null ? path.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "StandardError{" +
//                "timestamp=" + timestamp +
//                ", status=" + status +
//                ", error='" + error + '\'' +
//                ", path='" + path + '\'' +
//                '}';
//    }

}
