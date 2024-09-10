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

import io.netty.channel.epoll.Epoll;
import io.netty.channel.kqueue.KQueue;
import io.vertx.core.impl.transports.EpollTransport;
import io.vertx.core.impl.transports.IoUringTransport;
import io.vertx.core.impl.transports.KQueueTransport;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
class TransportLoader {

  static Transport epoll() {
    try {
      EpollTransport impl = new EpollTransport();
      boolean available = impl.isAvailable();
      Throwable unavailabilityCause = impl.unavailabilityCause();
      return new Transport() {
        @Override
        public String name() {
          return "epoll";
        }
        @Override
        public boolean available() {
          return available;
        }
        @Override
        public Throwable unavailabilityCause() {
          return unavailabilityCause;
        }
        @Override
        public io.vertx.core.spi.transport.Transport implementation() {
          return impl;
        }
      };
    } catch (Throwable ignore) {
      // Jar not here
    }
    return null;
  }

  static Transport io_uring() {
    try {
      IoUringTransport impl = new IoUringTransport();
      boolean available = impl.isAvailable();
      Throwable unavailabilityCause = impl.unavailabilityCause();
      return new Transport() {
        @Override
        public String name() {
          return "io_uring";
        }
        @Override
        public boolean available() {
          return available;
        }
        @Override
        public Throwable unavailabilityCause() {
          return unavailabilityCause;
        }
        @Override
        public io.vertx.core.spi.transport.Transport implementation() {
          return impl;
        }
      };
    } catch (Throwable ignore) {
      // Jar not here
    }
    return null;
  }

  static Transport kqueue() {
    try {
      KQueueTransport impl = new KQueueTransport();
      boolean available = impl.isAvailable();
      Throwable unavailabilityCause = impl.unavailabilityCause();
      return new Transport() {
        @Override
        public String name() {
          return "kqueue";
        }
        @Override
        public boolean available() {
          return available;
        }
        @Override
        public Throwable unavailabilityCause() {
          return unavailabilityCause;
        }
        @Override
        public io.vertx.core.spi.transport.Transport implementation() {
          return impl;
        }
      };
    } catch (Throwable ignore) {
      // Jar not here
    }
    return null;
  }
}
