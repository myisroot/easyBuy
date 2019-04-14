package com.easybuynet.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlUtil {


    /**
     * 获取sql语句
     *
     * @param object
     * @return
     */
    private Object var3 = null;

    public StringBuffer getSql(Object object, SqlParam param) {
        StringBuffer sql = new StringBuffer();
        if (null != object && null != param) {
            try {
                int var1 = 0;
                Class<?> clazz = object.getClass();
                Field[] fields = clazz.getDeclaredFields();
                String simpleName = clazz.getSimpleName();
                int var2 = 0;
                //5
                if (fields[0].getName().equalsIgnoreCase("serialVersionUID")) {
                    var2 = fields.length - 1;
                } else {
                    var2 = fields.length;
                }
                //判断是否是id字段

                if (fields[0].getName().equalsIgnoreCase("serialVersionUID")) {
                    var3 = fields[1].getName();
                } else {
                    var3 = fields[0].getName();
                }
                if (param.equals(SqlParam.SELECT)) {
                    //拼接查询语句
                    sql.append("select ");
                    for (Field field : fields) {
                        //拼接查询字段
                        //设置为可访问
                        field.setAccessible(true);
                        var1++;
                        if (!field.getName().equalsIgnoreCase("serialVersionUID")) {
                            if (var1 < fields.length) {
                                sql.append(field.getName() + " , ");
                            } else {
                                sql.append(field.getName() + " ");
                            }
                        }
                    }
                    sql.append(" from " + simpleName + " ");
                    var1 = 0;
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object fieldVal = field.get(object);
                        if (fieldVal != null && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                            String val = fieldVal.toString();
                            if (var1 == 0) {
                                if (("$").equals(val.substring(0, 1))) {
                                    //判断是否是第一个参数
                                    sql.append(" where " + field.getName() + " like ? ");
                                } else {
                                    //判断是否是第一个参数
                                    sql.append(" where " + field.getName() + " = ? ");
                                }
                            } else {
                                if (("$").equals(val.substring(0, 1))) {
                                    sql.append(" and " + field.getName() + " like ?  ");
                                } else {
                                    sql.append(" and " + field.getName() + " = ?  ");
                                }
                            }
                            var1++;
                        }
                    }
                    //增加
                } else if (param.equals(SqlParam.INSERT)) {
                    //拼接增加语句
                    sql.append("insert into " + simpleName + " values (");
                    for (int i = 0; i < var2; i++) {
                        if (i != var2 - 1) {
                            sql.append("?,");
                        } else {
                            sql.append("?");
                        }
                    }
                    sql.append(")");
                    //删除
                } else if (param.equals(SqlParam.DELETE)) {
                    //拼接删除语句
                    sql.append("DELETE FROM " + simpleName + " WHERE " + var3 + "=?");
                } else if (param.equals(SqlParam.UPDATE)) {
                    //拼接修改语句
                    sql.append("update " + simpleName + " set ");
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (!field.getName().equalsIgnoreCase("serialVersionUID") &&
                                !field.getName().equalsIgnoreCase(var3.toString())
                        ) {
                            if (var1 != var2) {
                                sql.append(field.getName() + " = ? , ");
                            } else {
                                sql.append(field.getName() + "=? ");
                            }
                        }
                        var1++;
                    }

                    sql.append("where " + var3 + "= ?");
                } else if (null == param) {
                    return null;
                }
                return sql;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取sql参数
     *
     * @param object
     * @return
     */
    public List<Object> getSqlParams(Object object, SqlParam param) {
        try {
            if (null != object && null != param) {
                List<Object> sqlParams = new ArrayList<Object>();
                Class<?> clazz = object.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object fieldVal = field.get(object);
                    if (param.equals(SqlParam.SELECT)) {
                        //处理查询字段参数
                        if (fieldVal != null && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                            if ("$".equals(fieldVal.toString().substring(0, 1))) {
                                sqlParams.add("%" + fieldVal.toString().replace("$", "") + "%");
                            } else {
                                sqlParams.add(fieldVal);
                            }
                        }
                    } else if (param.equals(SqlParam.INSERT)) {
                        //处理增加的字段参数
                        if (!field.getName().equalsIgnoreCase("serialVersionUID")) {
                            sqlParams.add(fieldVal);
                        }
                    } else if (param.equals(SqlParam.UPDATE)) {
                        //处理修改的字段参数
                        if (!field.getName().equalsIgnoreCase("serialVersionUID") &&
                                !field.getName().equalsIgnoreCase(var3.toString())
                        ) {
                            sqlParams.add(fieldVal);
                        }
                    } else if (null == param) {
                        return null;
                    }
                }
                //如果是删除或者是修改增加一个id参数
                if (param.equals(SqlParam.UPDATE) || param.equals(SqlParam.DELETE)) {
                    if (fields[0].getName().equalsIgnoreCase("serialVersionUID")) {
                        fields[1].setAccessible(true);
                        sqlParams.add(fields[1].get(object));
                    } else {
                        fields[0].setAccessible(true);
                        sqlParams.add(fields[0].get(object));
                    }
                }
                return sqlParams;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
