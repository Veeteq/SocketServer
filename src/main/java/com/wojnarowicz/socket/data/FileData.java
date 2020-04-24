package com.wojnarowicz.socket.data;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String destinationDirectory;
    private String sourceDirectory;
    private String fileName;
    private long fileSize;
    private byte[] fileData;
    private String status;

    public FileData() {
    }

    public FileData(Path path) {
        try {
            File file = path.toFile();
            this.sourceDirectory = file.getParent();
            this.fileName = file.getName();
            this.fileSize = file.length();
            this.fileData = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FileData [destinationDirectory=" + destinationDirectory + ", sourceDirectory=" + sourceDirectory
                + ", fileName=" + fileName + ", fileSize=" + fileSize + "]";
    }
}
