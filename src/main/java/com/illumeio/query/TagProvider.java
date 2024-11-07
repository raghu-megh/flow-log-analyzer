package com.illumeio.query;

import java.util.Optional;
import java.util.function.Supplier;

public interface TagProvider {

    Optional<String> getTag(Supplier<String> keyProvider);
}
