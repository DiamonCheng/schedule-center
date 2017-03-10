package com.frame.core.query.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.frame.core.query.xml.definition.*;
import org.springframework.util.StringUtils;

public class QueryHqlResolver {
	public static String generateSelect(QueryDefinition definition, QueryConditions condition){
		StringBuilder sb=new StringBuilder();
		appendSelectFields(sb, definition, condition.getConditions());
		appendFrom(sb, definition, condition.getConditions());
		appendWhere(sb, definition, condition.getConditions());
		appendSort(sb, definition, condition);
		return sb.toString();
	}
	public static String generateCount(QueryDefinition definition, List<QueryCondition> conditions){
		StringBuilder sb=new StringBuilder();
		appendCount(sb);
		appendFrom(sb, definition, conditions);
		appendWhere(sb, definition, conditions);
		return sb.toString();
	}
	public static void appendCount(StringBuilder sb){
		sb.append("select count(0) ");
	}
	public static void appendSelectFields(StringBuilder sb, QueryDefinition definition, List<QueryCondition> conditions){
		List<ColumnDefinition> columns=definition.getColumns();
		if (columns==null||columns.size()==0) throw new NullPointerException("pageDefinetion->querydefinition->columns 中的列定义不能为空");
		if (definition.getMappedClass().size()==0) throw new NullPointerException("pageDefinetion->querydefinition->mappedClass 中的实体定义不能为空");
		sb.append(" select ");
		MappedClassEntry coreEntry=definition.getMappedClass().get(0);
		if (!StringUtils.isEmpty(coreEntry.getAlias())) sb.append(coreEntry.getAlias()).append('.');
		sb.append("id as id, ");
		int index=0;
		for (Iterator<ColumnDefinition> iterator = columns.iterator(); iterator.hasNext(); index++) {
			ColumnDefinition column = iterator.next();
			if (column.getStaticColumnData()!=null) continue;
			if (!StringUtils.isEmpty(column.getFromAlias())) sb.append(column.getFromAlias()).append('.');
			sb.append(column.getField());
			sb.append(" as col_"+index);
			sb.append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
	}
	public static void appendFrom(StringBuilder sb, QueryDefinition definition, List<QueryCondition> conditions){
		sb.append(" from ");// MenuEntity p join p.children as child 
		List<MappedClassEntry> mappedClasses=definition.getMappedClass();
		for (Iterator<MappedClassEntry> iterator = mappedClasses.iterator(); iterator.hasNext();) {
			MappedClassEntry e = iterator.next();
			sb.append(e.getMappedClass().getName()).append(" ");
			if (!StringUtils.isEmpty(e.getAlias())) sb.append(e.getAlias()).append(" ");
			List<JoinEntry> joins=e.getJoin();
			if (joins!=null&&joins.size()!=0) for (JoinEntry joinEntry : joins) {
				if (!StringUtils.isEmpty(joinEntry.getType())) sb.append(joinEntry.getType()).append(" ");
				sb.append("join ");
				if (!StringUtils.isEmpty(e.getAlias())) sb.append(e.getAlias()).append(".");
				sb.append(joinEntry.getField()).append(" ");
				if (!StringUtils.isEmpty(joinEntry.getAs())) sb.append(" as ").append(joinEntry.getAs()).append(" ");
			}
			if (iterator.hasNext()) sb.append(",");
		}
	}
	public static  void appendWhere(StringBuilder sb, QueryDefinition definition, List<QueryCondition> conditions){
		if (conditions==null||conditions.size()==0) return;
		sb.append(" where 1=1 ");
		if (!StringUtils.isEmpty(definition.getWhere())) sb.append("and ").append(definition.getWhere()).append(" ");
		for (QueryCondition condition : conditions) {
			if (StringUtils.isEmpty(condition.getValue())) continue;
			sb.append("and ");
			if (!StringUtils.isEmpty(condition.getAlias())) sb.append(condition.getAlias()).append(".");
			sb.append(condition.getField()).append(" ");
			sb.append(condition.getOperator()==null?"=":condition.getOperator());
			sb.append(" ? ");
		}
	}
	public static void appendSort(StringBuilder sb, QueryDefinition definition, QueryConditions conditions){
		List<SortEntry> sortEntries=conditions.getSortEntries();
		if (sortEntries.size()==0) return;
		sb.append("order by ");
		int index=0;
		for (SortEntry sortEntry:sortEntries){
			if (!StringUtils.isEmpty(sortEntry.getFromAlias())) sb.append(sortEntry.getFromAlias()).append(".");
			sb.append(sortEntry.getField()).append(" ");
			sb.append(sortEntry.getOrder());
			if (index<sortEntries.size()-1) sb.append(",");
			index++;
		}
	}
	public static void main(String[] args) {
		System.out.println(List.class.isAssignableFrom(ArrayList.class));
	}
}
