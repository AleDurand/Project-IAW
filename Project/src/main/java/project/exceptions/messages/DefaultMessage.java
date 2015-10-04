package project.exceptions.messages;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Default error message", description = "Default error message representation")
public class DefaultMessage implements Serializable {
    @ApiModelProperty(name = "Status", value = "HTTP status code of the error", required = true)
    private Integer status;
    @ApiModelProperty(name = "Message", value = "Additional information of the error", required = true)
    private String message;

    public DefaultMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
