/*
 * Copyright (c) 2011-2024 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 */
package io.vertx.core.transport;

import io.vertx.core.impl.transports.JDKTransport;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public interface Transport {

  Transport JDK = new Transport() {
    @Override
    public String name() {
      return "jdk";
    }
    @Override
    public boolean available() {
      return true;
    }
    @Override
    public Throwable unavailabilityCause() {
      return null;
    }

    @Override
    public io.vertx.core.spi.transport.Transport implementation() {
      return JDKTransport.INSTANCE;
    }
  };

  Transport KQUEUE = TransportLoader.kqueue();

  Transport EPOLL = TransportLoader.epoll();

  Transport IO_URING = TransportLoader.io_uring();

  String name();

  /**
   * Return a native transport suitable for the OS
   *
   * <ul>
   *   <li>{@link #EPOLL} on Linux</li>
   *   <li>{@link #KQUEUE} on Mac</li>
   * </ul>
   *
   * @return a native transport, notice it might return an unavailable transport ({@link Transport#available()}), in such case {@link Transport#unavailabilityCause()}
   * should be used to check the error preventing using it
   */
  static Transport nativeTransport() {
    return KQUEUE != null ? KQUEUE : EPOLL;
  }

  boolean available();

  Throwable unavailabilityCause();

  io.vertx.core.spi.transport.Transport implementation();

}
