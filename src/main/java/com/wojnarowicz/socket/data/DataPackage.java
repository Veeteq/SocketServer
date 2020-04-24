package com.wojnarowicz.socket.data;

import java.io.Serializable;

public class DataPackage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private long length;
    private byte[] file;

    public DataPackage(String name, long length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "DataPackage [name=" + name + ", length=" + length + "]";
    }
}
