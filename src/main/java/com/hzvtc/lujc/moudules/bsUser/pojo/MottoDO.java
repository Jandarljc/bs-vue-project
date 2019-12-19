package com.hzvtc.lujc.moudules.bsUser.pojo;

import java.util.Date;

public class MottoDO {
    private Long id;

    private String typeOne;

    private String typeTwo;

    private String content;

    private Integer sequence;

    private String valid;

    private Date releaseTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOne() {
        return typeOne;
    }

    public void setTypeOne(String typeOne) {
        this.typeOne = typeOne == null ? null : typeOne.trim();
    }

    public String getTypeTwo() {
        return typeTwo;
    }

    public void setTypeTwo(String typeTwo) {
        this.typeTwo = typeTwo == null ? null : typeTwo.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}