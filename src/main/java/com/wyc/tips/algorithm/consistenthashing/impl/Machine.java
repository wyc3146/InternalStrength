package com.wyc.tips.algorithm.consistenthashing.impl;/**
 * Created by wyc on 2016/12/26.
 */

import com.wyc.tips.algorithm.consistenthashing.IMachine;

/**
 * @author wangyongcan
 * @Date 2016/12/26 21:14
 */
public class Machine implements IMachine {
    private String ip;
    private int port;
    private int id;
    private String name;

    private static int idIncrement = 0;

    public Machine(String ip,int port,String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.id = idIncrement++;
    }

    public Machine(String ip,int port) {
        this(ip,port,ip+":"+port);
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void set(String key, Object value) {

    }

    @Override
    public Object get(String key) {
        return null;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ip:%s,port:%s,name:%s",ip,port,name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Machine)) {
            return false;
        }
        Machine machine = (Machine) obj;
        return (this.ip + ":" + this.port).equals(machine.ip + ":" + machine.port);
    }
}
