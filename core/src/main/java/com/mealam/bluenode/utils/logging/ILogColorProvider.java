package com.mealam.bluenode.utils.logging;

import java.util.logging.Level;

@FunctionalInterface
public interface ILogColorProvider {

    String getColor(Level pLevel);
}
