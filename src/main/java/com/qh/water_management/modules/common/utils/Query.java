package com.qh.water_management.modules.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.qh.water_management.modules.common.xss.SQLFilter;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 13:15
 * @Description: 查询参数
 */
@Getter
@Setter
public class Query extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    //当前页码
    private int page;
    //每页条数
    private int rows;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        if (params.keySet().contains("page") && params.keySet().contains("rows")) {
            this.page = Integer.valueOf(params.get("page").toString());
            this.rows = Integer.valueOf(params.get("rows").toString());

            this.put("offset", (page - 1) * rows);
            this.put("page", page);
            this.put("limit", rows);
        }

        //防止SQL注入（因为sort,order是通过拼接SQL实现排序的，会有SQL注入风险）
        if (params.keySet().contains("sort"))  {
            String sort = params.get("sort").toString();
            this.put("sort", SQLFilter.sqlInject(sort));
        }
        if (params.keySet().contains("order")) {
            String order = params.get("order").toString();
            this.put("order", SQLFilter.sqlInject(order));
        }
        //过滤条件
        Object filterRules = params.get("filterRules");
        if (filterRules != null) {
            List<Map> rules =  JSONObject.parseArray(filterRules.toString(), Map.class);
            StringBuilder sb = new StringBuilder();
            if (rules != null && rules.size() > 0) {
                rules.parallelStream().forEach( p -> {
                    sb.append(" and").append(" ").append(p.get("field")).append(" ");
                    switch (p.get("op").toString()) {
                        case "equal":
                            sb.append("=").append("'").append(p.get("value")).append("'");
                            break;
                        case "contains":
                            sb.append("like ").append("'%").append(p.get("value")).append("%'");
                            break;
                        case "notequal":
                            sb.append("!= ").append("'").append(p.get("value")).append("'");
                            break;
                        case "greater":
                            sb.append("> ").append("'").append(p.get("value")).append("'");
                            break;
                        case "greaterorequal":
                            sb.append(">= ").append("'").append(p.get("value")).append("'");
                            break;
                        case "less":
                            sb.append("< ").append("'").append(p.get("value")).append("'");
                            break;
                        case "lessorequal":
                            sb.append("<= ").append("'").append(p.get("value")).append("'");
                            break;
                        case "beginwith":
                            sb.append("like ").append("'").append(p.get("value")).append("%'");
                            break;
                        case "endwith":
                            sb.append("like ").append("'%").append(p.get("value")).append("'");
                            break;
                        default:
                            break;
                    }
                });
                this.put("filterRules", sb.toString());
            } else {
                this.remove("filterRules");
            }
        }

        //查询系统
        Object advanceFilter = params.get("advanceFilter");
        if (advanceFilter != null) {
            List<Map> advances = JSONObject.parseArray(advanceFilter.toString(), Map.class);
            StringBuilder sb = new StringBuilder();
            if (advances != null && advances.size() > 0) {
                advances.parallelStream().forEach(p -> {
                    sb.append(" ").append(p.get("join")).append(" ").append(p.get("lb")).append(p.get("field")).append(" ");
                    switch (p.get("op").toString()) {
                        case "equal":
                            sb.append("=").append("'").append(p.get("value")).append("'");
                            break;
                        case "contains":
                            sb.append("like ").append("'%").append(p.get("value")).append("%'");
                            break;
                        case "notequal":
                            sb.append("!= ").append("'").append(p.get("value")).append("'");
                            break;
                        case "greater":
                            sb.append("> ").append("'").append(p.get("value")).append("'");
                            break;
                        case "greaterorequal":
                            sb.append(">= ").append("'").append(p.get("value")).append("'");
                            break;
                        case "less":
                            sb.append("< ").append("'").append(p.get("value")).append("'");
                            break;
                        case "lessorequal":
                            sb.append("<= ").append("'").append(p.get("value")).append("'");
                            break;
                        case "beginwith":
                            sb.append("like ").append("'").append(p.get("value")).append("%'");
                            break;
                        case "endwith":
                            sb.append("like ").append("'%").append(p.get("value")).append("'");
                            break;
                        default:
                            break;
                    }
                });
                this.put("advanceFilter", sb.toString());
            } else {
                this.remove("advanceFilter");
            }
        }

        if (params.keySet().contains("puuid")) {
            String puuid = (String) params.get("puuid");
            this.put("puuid", puuid);
        }

        if (params.keySet().contains("code")) {
            String code = (String) params.get("code");
            this.put("code", code);
        }

        if(params.keySet().contains("uuid")) {
            String uuid = (String) params.get("uuid");
            this.put("orgId", uuid);
        }
    }

}
