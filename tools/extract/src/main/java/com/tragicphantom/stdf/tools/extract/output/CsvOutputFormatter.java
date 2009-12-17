/**
 * Copyright 2009 tragicphantom
 *
 * This file is part of stdf4j.
 *
 * Stdf4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Stdf4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with stdf4j.  If not, see <http://www.gnu.org/licenses/>.
**/
package com.tragicphantom.stdf.tools.extract.output;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;

import com.tragicphantom.stdf.Record;

public class CsvOutputFormatter implements OutputFormatter{
   private String lastHeaderType = "";

   public CsvOutputFormatter(){
   }

   private String join(Iterable<?> values, String delim){
      boolean       useDelim = false;
      StringBuilder sb       = new StringBuilder();
      for(Object value : values){
         if(useDelim)
            sb.append(delim);
         else
            useDelim = true;

         if(value != null)
            sb.append(value.toString());
      }
      return sb.toString();
   }

   public void write(Record record){
      String                  type   = record.getType().toUpperCase();
      TreeMap<String, Object> fields = new TreeMap<String, Object>(record.getFields());

      if(!type.equals(lastHeaderType)){
         lastHeaderType = type;

         Set<String>       keys    = fields.keySet();
         ArrayList<String> newKeys = new ArrayList<String>();

         for(String key : keys)
            newKeys.add(type + ":" + key);

         System.out.println(join(newKeys, ","));
      }

      System.out.println(join(fields.values(), ","));
   }
}
