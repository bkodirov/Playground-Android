package git.playground.android.datalayer.date;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.time.Instant;

@SuppressWarnings("unused") // Accessed via reflection by Moshi.
public final class InstantAdapter {
  @ToJson
  public String toJson(Instant instant) {
    return instant.toString();
  }

  @FromJson
  public Instant fromJson(String value) {
    return Instant.parse(value);
  }
}