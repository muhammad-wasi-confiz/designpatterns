package org.confiz.builder;


public class Configuration {
    private final String url;
    private final int connectTimeout;
    private final int socketTimeout;
    private final int requestTimeout;
    private final boolean ignoreErrors;
    private final boolean debug;

    private Configuration(Builder builder) {
        this.url = builder.url;
        this.connectTimeout = builder.connectTimeout;
        this.socketTimeout = builder.socketTimeout;
        this.requestTimeout = builder.requestTimeout;
        this.ignoreErrors = builder.ignoreErrors;
        this.debug = builder.debug;
    }

    public String getUrl() {
        return url;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public boolean isIgnoreErrors() {
        return ignoreErrors;
    }

    public boolean isDebug() {
        return debug;
    }

    /**
     * Method to get a new instance of builder
     *
     * @return new instance of builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String url;
        private Integer connectTimeout = 30000;
        private Integer socketTimeout = -1;
        private Integer requestTimeout = 0;
        private boolean ignoreErrors;
        private boolean debug;

        /**
         * Used to specify the url which will later use to create HttpUriRequest
         *
         * @param url url
         * @return instance of this {@link Builder}
         */
        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }


        /**
         * Used to specify the connection timeout (in ms).
         * A timeout value of zero is interpreted as an infinite timeout.
         * A negative value is interpreted as undefined (system default).
         * <p>
         * Default value is {@code 30000}
         * </p>
         *
         * @param connectTimeout
         * @return instance of this {@link Builder}
         */
        public Builder withConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * Used to specify the socket timeout (in ms).
         * A timeout value of zero is interpreted as an infinite timeout.
         * A negative value is interpreted as undefined (system default).
         * <p>
         * Default value is {@code -1}
         * </p>
         *
         * @param socketTimeout
         * @return instance of this {@link Builder}
         */
        public Builder withSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }

        /**
         * Used to specify the request timeout (in ms).
         * A timeout value of zero is interpreted as an infinite timeout.
         *
         * @param requestTimeout
         * @return instance of this {@link Builder}
         */
        public Builder withRequestTimeout(int requestTimeout) {
            this.requestTimeout = requestTimeout;
            return this;
        }

        /**
         * Used to ignore the errors produced by the http task in flow
         *
         * @param ignoreErrors
         * @return instance of this {@link Builder}
         */
        public Builder withIgnoreErrors(boolean ignoreErrors) {
            this.ignoreErrors = ignoreErrors;
            return this;
        }

        public Builder withDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        /**
         * Invoking this method will result in a new configuration
         *
         * @return new instance of this {@link Configuration}
         */
        public Configuration build() {
            return new Configuration(this);
        }
    }
}
