package com.redsun.netty.jboss.marshalling;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SubscribeReq implements Serializable {

    /**
     * 默认的序列号
     */
    private static final long serialVersionUID = 1L;

    private int subReqID;

    private String userName;

    private String productName;

    private String phoneNamber;

    private String address;
}
