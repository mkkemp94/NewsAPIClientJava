package com.mkemp.newsapiclientjava.data.db;

import com.mkemp.newsapiclientjava.data.model.Source;

import androidx.room.TypeConverter;

public class Converters
{
    @TypeConverter
    public String fromSource(Source source)
    {
        return source.getName();
    }

    @TypeConverter
    public Source toSource(String name)
    {
        return new Source(name, name);
    }
}
