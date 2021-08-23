package com.murilonerdx.restapispring.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Data
public class UploadFileResponseDTO implements Serializable {
    private String fileName;
    private String fileDownloadUri;
    private Date fileType;
    private long size;
}