package com.tinnkm.application.util.iview.table;

import java.util.List;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname TableColumn
 * @description 适配iview的Table里的column数据
 * @date 2018/6/12 10:02
 **/
public class TableColumn {
    /**
     * 列类型
     */
    private String type;
    /**
     * 列头显示文字
     */
    private String title;
    /**
     * 对应字段
     */
    private String key;
    private int width;
    private int minWidth;
    private int maxWidth;
    private String align;
    /**
     * 列样式
     */
    private String className;
    private String fixed;
    private Boolean ellipsis;
    private String render;
    private String renderHeader;
    private Boolean sortable;
    private String sortMethod;
    private String sortType;
    private String[] filters;
    private String filterMethod;
    private Boolean filterMultiple;
    private String[] filteredValue;
    private String filterRemote;
    private List<TableColumn> children;

    public TableColumn() {
    }

    public TableColumn(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public TableColumn(String title, String key, List<TableColumn> children) {
        this.title = title;
        this.key = key;
        this.children = children;
    }

    public TableColumn(String type, String title, String key, int width, int minWidth, int maxWidth, String align, String className, String fixed, Boolean ellipsis, String render, String renderHeader, Boolean sortable, String sortMethod, String sortType, String[] filters, String filterMethod, Boolean filterMultiple, String[] filteredValue, String filterRemote, List<TableColumn> children) {
        this.type = type;
        this.title = title;
        this.key = key;
        this.width = width;
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.align = align;
        this.className = className;
        this.fixed = fixed;
        this.ellipsis = ellipsis;
        this.render = render;
        this.renderHeader = renderHeader;
        this.sortable = sortable;
        this.sortMethod = sortMethod;
        this.sortType = sortType;
        this.filters = filters;
        this.filterMethod = filterMethod;
        this.filterMultiple = filterMultiple;
        this.filteredValue = filteredValue;
        this.filterRemote = filterRemote;
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public Boolean getEllipsis() {
        return ellipsis;
    }

    public void setEllipsis(Boolean ellipsis) {
        this.ellipsis = ellipsis;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getRenderHeader() {
        return renderHeader;
    }

    public void setRenderHeader(String renderHeader) {
        this.renderHeader = renderHeader;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    public String getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(String sortMethod) {
        this.sortMethod = sortMethod;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String[] getFilters() {
        return filters;
    }

    public void setFilters(String[] filters) {
        this.filters = filters;
    }

    public String getFilterMethod() {
        return filterMethod;
    }

    public void setFilterMethod(String filterMethod) {
        this.filterMethod = filterMethod;
    }

    public Boolean getFilterMultiple() {
        return filterMultiple;
    }

    public void setFilterMultiple(Boolean filterMultiple) {
        this.filterMultiple = filterMultiple;
    }

    public String[] getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(String[] filteredValue) {
        this.filteredValue = filteredValue;
    }

    public String getFilterRemote() {
        return filterRemote;
    }

    public void setFilterRemote(String filterRemote) {
        this.filterRemote = filterRemote;
    }

    public List<TableColumn> getChildren() {
        return children;
    }

    public void setChildren(List<TableColumn> children) {
        this.children = children;
    }
}
