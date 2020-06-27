package vn.edu.ntu.dinhtuyen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.edu.ntu.dinhtuyen.model.CustomerEntity;

@Dao
public interface CustomerDao
{

    @Query("select * from customer")
    List<CustomerEntity> findAll();

    @Insert
    void insert(CustomerEntity customerEntity);

    @Delete
    void delete(CustomerEntity customerEntity);

    @Update
    void update(CustomerEntity customerEntity);
}
