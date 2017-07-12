package com.ucf.first.ao;

public class BeetleArticle {
    private Integer id;

    private String resName;

    private String level;

    private String nodeId;

    private String title;

    private String subtitle;

    private String intro;

    private String pic;

    private Integer viewOrder;

    private Byte status;

    private Integer pubTime;

    private Integer updateUserId;

    private Integer updateTime;

    private Integer createUserId;

    private Integer createTime;

    private String allow;

    private String content;

    public BeetleArticle(Integer id, String resName, String level, String nodeId, String title, String subtitle, String intro, String pic, Integer viewOrder, Byte status, Integer pubTime, Integer updateUserId, Integer updateTime, Integer createUserId, Integer createTime, String allow, String content) {
        this.id = id;
        this.resName = resName;
        this.level = level;
        this.nodeId = nodeId;
        this.title = title;
        this.subtitle = subtitle;
        this.intro = intro;
        this.pic = pic;
        this.viewOrder = viewOrder;
        this.status = status;
        this.pubTime = pubTime;
        this.updateUserId = updateUserId;
        this.updateTime = updateTime;
        this.createUserId = createUserId;
        this.createTime = createTime;
        this.allow = allow;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getResName() {
        return resName;
    }

    public String getLevel() {
        return level;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIntro() {
        return intro;
    }

    public String getPic() {
        return pic;
    }

    public Integer getViewOrder() {
        return viewOrder;
    }

    public Byte getStatus() {
        return status;
    }

    public Integer getPubTime() {
        return pubTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public String getAllow() {
        return allow;
    }

    public String getContent() {
        return content;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public void setResName(String resName) {
        this.resName=resName;
    }

    public void setLevel(String level) {
        this.level=level;
    }

    public void setNodeId(String nodeId) {
        this.nodeId=nodeId;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle=subtitle;
    }

    public void setIntro(String intro) {
        this.intro=intro;
    }

    public void setPic(String pic) {
        this.pic=pic;
    }

    public void setViewOrder(Integer viewOrder) {
        this.viewOrder=viewOrder;
    }

    public void setStatus(Byte status) {
        this.status=status;
    }

    public void setPubTime(Integer pubTime) {
        this.pubTime=pubTime;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId=updateUserId;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime=updateTime;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId=createUserId;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime=createTime;
    }

    public void setAllow(String allow) {
        this.allow=allow;
    }

    public void setContent(String content) {
        this.content=content;
    }

    public BeetleArticle() {
        super();
    }
}