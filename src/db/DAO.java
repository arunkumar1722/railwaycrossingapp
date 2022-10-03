

package db;

import java.util.Map;

public interface DAO {
    boolean set(Object var1);

    boolean delete(Object var1);

    Map<String, ?> retrieve(Object var1);

    Object retrieve(String var1);
}
