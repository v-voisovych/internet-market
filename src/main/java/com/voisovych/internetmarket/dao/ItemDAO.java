package com.voisovych.internetmarket.dao;

import com.voisovych.internetmarket.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ItemDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int save(Item item){
        String sql = "INSERT INTO items(name_items, description, numbers, price) values('"+item.getName()+"', '"+item.getDescription()+"', "+item.getNumber()+", "+item.getPrice()+")";
        return jdbcTemplate.update(sql);
    }

    public int update(Item item){
        String sql = "UPDATE items SET name_items = '"+item.getName()+"', description = '"+item.getDescription()+"', numbers = "+item.getNumber()+",price = "+item.getPrice()+" WHERE id ="+item.getId()+"";
        return jdbcTemplate.update(sql);
    }

    public List<Item> getItems(){
        return jdbcTemplate.query("SELECT * FROM items", new RowMapper<Item>(){
            public Item mapRow(ResultSet rs, int row) throws SQLException{
                Item item = new Item();
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setDescription(rs.getString(3));
                item.setNumber(rs.getInt(4));
                item.setPrice(rs.getInt(5));
                return item;
            }
        });
    }

    public List<Item> getById(int id){
        String sql = "SELECT * FROM items WHERE id = "+id+"";
        return jdbcTemplate.query(sql, new RowMapper<Item> (){
            public Item mapRow(ResultSet rs, int row) throws SQLException{
                Item item = new Item();
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setDescription(rs.getString(3));
                item.setNumber(rs.getInt(4));
                item.setPrice(rs.getInt(5));
                return item;
            }
        });
    }

    public int delete(int id){
        String sql = "DELETE FROM items WHERE id = "+id+"";
        return jdbcTemplate.update(sql);
    }

}

