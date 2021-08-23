package com.murilonerdx.restapispring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponseDTO implements Serializable {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}